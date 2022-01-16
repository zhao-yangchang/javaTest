package stream;

import common.VO.PeopleVO;
import common.VO.WorkerVO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className SortedTest
 * @description: sorted
 * @date 2022/1/16 10:54 下午
 */
public class SortedTest {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        // asc
        List<WorkerVO> asc = workerVOS
                .stream()
                .sorted(Comparator.comparing(WorkerVO::getSalary))
                .collect(Collectors.toList());
        System.out.println(asc);

        // sorted desc
        List<WorkerVO> desc = workerVOS
                .stream()
                .sorted((Comparator.comparing(WorkerVO::getSalary).reversed()))
                .collect(Collectors.toList());
        System.out.println(desc);

        // sorted * 2
        List<WorkerVO> salaryDescAgeAsc = workerVOS
                .stream()
                .sorted(
                        Comparator.comparing(WorkerVO::getSalary)
                                .reversed()
                                .thenComparing(PeopleVO::getAge)
                )
                .collect(Collectors.toList());
        System.out.println(salaryDescAgeAsc);

        // asc 2 自定义排序
        List<WorkerVO> asc2 = workerVOS
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.getSalary() == o2.getSalary()) {
                        return o1.getAge() - o2.getAge();
                    } else {
                        return o1.getSalary() - o2.getSalary();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(asc2);

    }

}
