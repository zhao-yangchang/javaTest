package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    private static List<String> list1 = Arrays.asList("zhangsan", "lisi", "wangwu");

    private static List<List<String>> list2 = new ArrayList<List<String>>(){
        {
            add(list1);
        }
    };

    public static void main(String[] args) {

        // System.out.println(list2);

        // 合并两次map
        List<String> list = list2
                .stream()
                .map(stringList -> {
                    return stringList.stream().map(s -> {
                        return s + "|";
                    }).collect(Collectors.toList());
                }).flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(list);
    }

}
