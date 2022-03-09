package zyc.javaTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName zyc.javaTest.MyTestApplication
 * @Description TODO
 * @Author zhaoyangchang
 * @Date 2022/3/7 上午9:39
 * @Version 1.0.0
 */
@SpringBootApplication
//@ComponentScan(basePackageClasses = DefaultConfig.class)
public class MyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTestApplication.class, args);
    }

}
