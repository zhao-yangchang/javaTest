package stream;

import common.vo.WorkerVO;

import java.util.List;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className reduceTest
 * @description: reduce(sum/min/max)
 * @date 2022/1/14 10:18 下午
 */
public class reduceTest {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        // sum1
        Integer sum1 = workerVOS
                .stream()
                .map(WorkerVO::getSalary)
                .reduce(Integer::sum)
                .orElse(null);
        System.out.println(sum1);

        // sum2
        Integer sum2 = workerVOS
                .stream()
                .reduce(0,
                        (sum, workerVO) -> sum += workerVO.getSalary(),
                        (i1, i2) -> i1 + i2);
        System.out.println(sum2);

        // sum3
        Integer sum3 = workerVOS
                .stream()
                .reduce(0,
                        (sum, workerVO) -> sum += workerVO.getSalary(),
                        Integer::sum);
        System.out.println(sum3);

        // max1
        Integer max1 = workerVOS
                .stream()
                .reduce(0,
                        (max, workerVO) -> max.compareTo(workerVO.getSalary()) > 0 ? max : workerVO.getSalary(),
                        Integer::max);
        System.out.println(max1);

        // max2
        Integer max2 = workerVOS
                .stream()
                .reduce(0,
                        (max, workerVO) -> max.compareTo(workerVO.getSalary()) > 0 ? max :workerVO.getSalary(),
                        (m1, m2) -> m1 > m2 ? m1 : m2);
        System.out.println(max2);

    }

}
