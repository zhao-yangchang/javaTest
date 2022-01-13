package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: StreamFlatMapTest
 * @description: flatMap test
 * @author zhaoyangchang
 * @date: 2022/1/8 10:14 下午
 * @version: 1.0.0
 */
public class StreamFlatMapTest {

    private static Stream<List<Integer>> intStream = Stream.of(
            Arrays.asList(4, 5),
            Arrays.asList(1, 3, 6),
            Arrays.asList(2, 8)
    );

    private static List<String> list1 = Arrays.asList("zhangsan", "lisi", "wangwu");

    private static List<List<String>> list2 = new ArrayList<List<String>>(){
        {
            add(list1);
        }
    };

    public static void main(String[] args) {

        // map
        List<String> mapList = list1
                .stream()
                .map(s -> s + "| map")
                .collect(Collectors.toList());
        System.out.println(mapList);

        // 将复合的集合扁平化
        List<Integer> flatI = intStream
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(flatI);

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
