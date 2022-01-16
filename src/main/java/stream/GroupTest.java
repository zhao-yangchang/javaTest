package stream;

import common.VO.PeopleVO;
import common.VO.WorkerVO;
import common.enums.SexyEnum;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className GroupTest
 * @description: partitioningBy/groupingBy
 * @date 2022/1/16 10:07 下午
 */
public class GroupTest {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        // partitioningBy
        Map<Boolean, List<WorkerVO>> partitioningBy = workerVOS
                .stream()
                .collect(Collectors.partitioningBy(workerVO ->
                        SexyEnum.WOMAN.getCode() == workerVO.getSex()));
        System.out.println(partitioningBy.toString());

        // groupBy
        Map<Integer, List<WorkerVO>> sexyMap = workerVOS
                .stream()
                .collect(Collectors.groupingBy(PeopleVO::getSex, Collectors.toList()));
        System.out.println(sexyMap);

        // groupBy * 2
        Map<Integer, Map<String, List<WorkerVO>>> sexyAreaMap = workerVOS
                .stream()
                .collect(
                        Collectors.groupingBy(WorkerVO::getSex,
                                Collectors.groupingBy(WorkerVO::getArea))
                );
        System.out.println(sexyAreaMap);

    }

}
