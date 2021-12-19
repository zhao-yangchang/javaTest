package common.enums;

import lombok.Getter;

/**
 * 性别
 */
@Getter
public enum SexyEnum {

    MAN(1, "男"),
    WOMAN(2, "女"),
    ;

    private int         code;
    private String      desc;

    SexyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
