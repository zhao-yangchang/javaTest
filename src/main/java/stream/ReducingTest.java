package stream;

import common.vo.WorkerVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className ReducingTest
 * @description: reducing
 * @date 2022/1/16 10:41 下午
 */
public class ReducingTest {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        // reducing
        Integer sumSalary1 = workerVOS
                .stream()
                .collect(Collectors.reducing(0, WorkerVO::getSalary, (i1, i2) -> i1 + i2));
        System.out.println(sumSalary1);

        // reduce
        Integer sumSalary2 = workerVOS
                .stream()
                .reduce(0,
                        (integer, workerVO) -> integer + workerVO.getSalary(),
                        (integer, integer2) -> integer + integer2
                );
        System.out.println(sumSalary2);

    }

}
