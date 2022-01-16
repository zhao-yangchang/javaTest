package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className ExtractAndConcatTest
 * @description: concat/limit/skip
 * @date 2022/1/16 11:17 下午
 */
public class ExtractAndConcatTest {

    private static String[] strings1 = { "a", "c", "e", "g"};

    private static String[] strings2 = { "b", "d", "f", "g" };

    public static void main(String[] args) {

        Stream<String> stream1 = Stream.of(strings1);
        Stream<String> stream2 = Stream.of(strings2);

        // concat合并
        List<String> concat = Stream.concat(stream1, stream2)
                .sorted(String::compareTo)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(concat);

        // iterate + limit
        List<Integer> limit = Stream.iterate(1, integer -> 1 + integer)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(limit);

        // skip
        List<Integer> skip = Stream.iterate(1, integer -> 1 + integer)
                .skip(2)
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(skip);

    }

}
