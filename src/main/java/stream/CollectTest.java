package stream;

import common.VO.PeopleVO;
import common.VO.WorkerVO;
import common.enums.SexyEnum;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className CollectTest
 * @description: toList/toMap/toSet/maxBy/minBy/count...
 * @date 2022/1/15 4:32 下午
 */
public class CollectTest {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        // toList
        List<WorkerVO> toList = workerVOS
                .stream()
                .filter(workerVO -> SexyEnum.WOMAN.getCode() == workerVO.getSex())
                .collect(Collectors.toList());
        System.out.println(toList);

        // toMap
        Map<String, WorkerVO> voMap = workerVOS
                .stream()
                .collect(Collectors.toMap(PeopleVO::getName, Function.identity()));
        System.out.println(voMap);

        // toSet
        Set<String> nameSet = workerVOS
                .stream()
                .map(PeopleVO::getName)
                .collect(Collectors.toSet());
        System.out.println(nameSet);

        // sum
        Integer sumSalary = workerVOS
                .stream()
                .collect(Collectors.summingInt(WorkerVO::getSalary));
        System.out.println(sumSalary);

        // cout
        long count = workerVOS
                .stream()
                .collect(Collectors.counting());
        System.out.println(count);

        // average
        double average = workerVOS
                .stream()
                .collect(Collectors.averagingInt(workerVOS -> workerVOS.getSalary()));
        System.out.println(average);

        // max
        WorkerVO maxSalaryWorker = workerVOS
                .stream()
                .collect(Collectors.maxBy(Comparator.comparing(WorkerVO::getSalary)))
                .orElse(null);
        System.out.println(maxSalaryWorker);

        // min
        WorkerVO minSalaryWorker = workerVOS
                .stream()
                .collect(Collectors.minBy(Comparator.comparing(WorkerVO::getSalary)))
                .orElse(null);
        System.out.println(minSalaryWorker);

        // everyThing
        DoubleSummaryStatistics summaryStatistics = workerVOS
                .stream()
                .collect(Collectors.summarizingDouble(WorkerVO::getSalary));
        System.out.println(summaryStatistics);

    }

}
