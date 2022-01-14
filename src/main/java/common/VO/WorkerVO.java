package common.VO;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className WorkerVO
 * @description: 员工
 * @date 2022/1/14 10:21 下午
 */
@Data
public class WorkerVO extends PeopleVO{

    /**
     * 薪资
     */
    private Integer salary;

    public WorkerVO(Integer salary) {
        this.salary = salary;
    }

    public WorkerVO(String name, Integer age, int sex, Integer salary) {
        super(name, age, sex);
        this.salary = salary;
    }

    public static List<WorkerVO> getWorkers() {
        return Arrays.asList(
                new WorkerVO("zhangsan", 24, 1, 10000),
                new WorkerVO("lisi", 28, 1, 8500),
                new WorkerVO("wangwu", 25, 1, 15000),
                new WorkerVO("xiaohong", 22, 2, 9500),
                new WorkerVO("xiaosao", 26, 2, 12000),
                new WorkerVO("xiaonai", 24,  2, 11000)
        );
    }

}
