package common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class PeopleVO implements Serializable {
    private static final long serialVersionUID = 2843085915842646290L;

    private String      name;
    private Integer     age;
    private int         sex;

    public PeopleVO() {
    }

    public PeopleVO(String name, Integer age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static List<PeopleVO> getPeopleVOS() {
        return Arrays.asList(
                new PeopleVO("zhangsan", 24, 1),
                new PeopleVO("lisi", 28, 1),
                new PeopleVO("wangwu", 25, 1),
                new PeopleVO("xiaohong", 22, 2),
                new PeopleVO("xiaosao", 26, 2),
                new PeopleVO("xiaonai", 24, 2)
        );
    }
}
