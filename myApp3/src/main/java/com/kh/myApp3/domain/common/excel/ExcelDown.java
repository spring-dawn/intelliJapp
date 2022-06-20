package com.kh.myApp3.domain.common.excel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class ExcelDown {
    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");

//        1)
        List<String> result = givenList.stream()
                .collect(toList());
        System.out.println(result);

//        2)
        Set<String> result2 = givenList.stream()
                .collect(toSet());
        System.out.println(result2);

//        3)
        Map<String, Integer> result3 = givenList.stream()
                .collect(toMap(Function.identity(), String::length));
        System.out.println(result3);

//        4)
        Long result4 = givenList.stream()
                .collect(counting());
        System.out.println(result4);

//        5)
        Map<Integer, List<String>> result5 = givenList.stream()
                .collect(groupingBy(String::length, toList()));
        System.out.println(result5);

//        5-1)
        Map<Integer, Set<String>> result6 = givenList.stream()
                .collect(groupingBy(String::length, toSet()));
        System.out.println(result6);

//        6)
        Map<Boolean, List<String>> result7 = givenList.stream()
                .collect(partitioningBy(s -> s.length() > 2));
        System.out.println(result7);


//        *)
        String list = givenList.toString();
        System.out.println(list);

    }
}
