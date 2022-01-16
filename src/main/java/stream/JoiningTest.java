package stream;

import common.VO.PeopleVO;
import common.VO.WorkerVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className JoiningTest
 * @description: joining
 * @date 2022/1/16 10:34 下午
 */
public class JoiningTest {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        // joining1
        String nameString = workerVOS
                .stream()
                .map(PeopleVO::getName)
                .collect(Collectors.joining("-"));
        System.out.println(nameString);

        // joining2
        String areaString = workerVOS
                .stream()
                .map(WorkerVO::getArea)
                .collect(Collectors.joining(", ", "||-", "-||"));
        System.out.println(areaString);

    }

}
