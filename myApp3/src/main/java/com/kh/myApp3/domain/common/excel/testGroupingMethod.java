package com.kh.myApp3.domain.common.excel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class testGroupingMethod {
    public static void main(String[] args) {

            int index = 0;
            int[] array = new int[3];
            array[0] = 2;
            array[1] = 5;
            array[2] = 8;

            Loop1:
            for (int i = 0; i < array.length; i++) {
                for (int j = index; j < 10; j++) {
                    if (array[i] == j) {
                        System.out.println(array[i]);
                        index = j + 1;
                        continue Loop1;
                    }
                }
            }

        }


}

