package common.vo;

import common.enums.AreaEnum;
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

    /**
     * 地区
     */
    private String area;

    public WorkerVO() {
    }

    public WorkerVO(String name, Integer age, int sex, Integer salary, String area) {
        super(name, age, sex);
        this.salary = salary;
        this.area = area;
    }

    public static List<WorkerVO> getWorkers() {
        return Arrays.asList(
                new WorkerVO("zhangsan", 24, 1, 10000, AreaEnum.CHINA.getDesc()),
                new WorkerVO("lisi", 28, 1, 8500, AreaEnum.CHINA.getDesc()),
                new WorkerVO("wangwu", 25, 1, 15000, AreaEnum.AMERICA.getDesc()),
                new WorkerVO("xiaohong", 22, 2, 9500, AreaEnum.CHINA.getDesc()),
                new WorkerVO("xiaosao", 26, 2, 12000, AreaEnum.AMERICA.getDesc()),
                new WorkerVO("xiaonai", 24,  2, 11000, AreaEnum.AMERICA.getDesc()),
                new WorkerVO("sorted", 30,  2, 15000, AreaEnum.CHINA.getDesc())
        );
    }

}
