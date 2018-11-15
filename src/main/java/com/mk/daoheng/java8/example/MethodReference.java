package com.mk.daoheng.java8.example;

import com.google.common.base.Function;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MethodReference {

    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer(consumer, "aaa");

        useConsumer(System.out::println, "1231");

        List<Apple> appleList = Collections.emptyList();

        appleList.sort((a, b) -> {
            return 1;
        });
        appleList.sort((a, b) -> a.getColor().compareTo(b.getColor()));

        appleList.stream().forEach(a-> System.out.println(a));

        Function<String, Integer> stringIntegerFunction = Integer::parseInt;
        Integer apply = stringIntegerFunction.apply("123");
        System.out.println(apply);

        BiFunction<String, Integer, Character> stringIntegerCharacterBiFunction = String::charAt;
        System.out.println(stringIntegerCharacterBiFunction.apply("sfasfasfdfb", 3));

        // 使用 类名/对象名::方法名都可以实现这种方法的应用模式
        String str = "abc";
        Function<Integer, Character> integerCharacterFunction = str::charAt;
        Consumer tConsumer = System.out::print;
        tConsumer.accept(integerCharacterFunction.apply(1));


    }

    public static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

}
