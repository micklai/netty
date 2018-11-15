package com.mk.daoheng.java8.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExpression  {

    public static void main(String[] args) {
        Comparator<Apple> comparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        Comparator<Apple> comparator1 = (apple1,apple2) ->apple1.getColor().compareTo(apple2.getColor());

        List<Apple> list = Collections.emptyList();
        list.sort(comparator1);
        System.out.println(list);

        Function<String,Integer> StringFunciton = s -> s.length();   // String  入参   ，Integer 返回结果

        Predicate<Apple> p = a -> a.getColor().equals("green");

        Comparator<Apple> appleComparator = (a,b)->a.getColor().compareTo(b.getColor());

        Supplier<String> stringSupplier = String::new;
    }
}
