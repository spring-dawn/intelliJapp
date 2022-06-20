package com.kh.myApp3.domain.common.excel;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class testGroupingMethod {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("Foo", "Bar", "Bar", "Bar", "Foo"));

        Map<String, Long> result = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(result.keySet());
        System.out.println(result.get("Foo"));
        System.out.println(result.get("Bar"));


//        2)
        List<String> list2 = new ArrayList<>(Arrays.asList("Adam", "Bill", "Jack", "Joe", "Ian"));

        Map<Integer, Long> result2 = list.stream()
                .collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
        System.out.println(result2.get(3));
        System.out.println(result2.get(4));

//        3)frequency 로 특정 문자열이 몇 개나 있는지 찾기
        List<Integer> numbers = Arrays.asList(1, 2, 3, 1, 1, 2);
        System.out.println(Collections.frequency(numbers, 1));
        
        
    }
}

