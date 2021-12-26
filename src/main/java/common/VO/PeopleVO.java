package common.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PeopleVO implements Serializable {
    private static final long serialVersionUID = 2843085915842646290L;

    private String      name;
    private Integer     age;
    private int         sex;

    public PeopleVO(String name, Integer age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
