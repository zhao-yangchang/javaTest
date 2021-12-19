package functionInterface.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class People implements Serializable {
    private static final long serialVersionUID = 2843085915842646290L;

    private String      name;
    private Integer     age;
    private int         sex;

    public People(String name, Integer age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
