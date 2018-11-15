package com.mk.daoheng.java8.example;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class StreamExample {

    public static void main(String[] args) {
        // hava a dish list(menu)
        String[] strArray = {"1","2","3","4","5"};
        List<String> stringList = Arrays.asList(strArray);

        stringList.stream().filter(s -> Integer.valueOf(s)<5);
    }


}
