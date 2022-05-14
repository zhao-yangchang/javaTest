package com.zhaoyc.redisspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className User
 * @description: redis序列化测试类
 * @date 2022/5/14 21:05
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -8822917687099039826L;
    private String name;
    private Integer age;

}
