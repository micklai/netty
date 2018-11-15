package com.mk.daoheng.java8.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

    @FunctionalInterface  //只能有一个方法
    public static interface AppleFilter{
        boolean filter(Apple apple);

    }

    //策略模式
    public class GreanAplle implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    //使用内部类

    public static List<Apple> findApple(List<Apple> apples , AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();

        /*appleFilter = new AppleFilter(){
            @Override
            public boolean filter(Apple apple) {
                return true;
            }
        }*/;

        for(Apple apple : apples){
            if(appleFilter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> filterApple(List<Apple> appleList){
        List<Apple> list = new ArrayList<>();

        for(Apple apple : appleList){
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public  static  List<Apple> findApples(List<Apple> apples,String color){
        List<Apple> list = new ArrayList<>();

        for(Apple apple : apples){
            if(color.equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    };

    public static void main(String[] args) throws InterruptedException {
         List<Apple> apples = Arrays.asList(new Apple("green",160),new Apple("blue",200));
         List<Apple> greenApples = filterApple(apples);
         assert greenApples.size() == 1 : "錯誤"; //断言   java -ea AssertFoo  使用java -ea开启断言

        System.out.println(greenApples);


        List<Apple> blues = findApple(apples, (Apple apple) -> {
            return apple.getColor().equals("blue");
        });
        List<Apple> blues1 = findApple(apples, apple -> {
            return apple.getColor().equals("blue");
        });
        System.out.println(blues);


        new Thread(()->{
           for(int i=0;i<10;i++) {
               if (i == 5) {
                   break;
               }
               System.out.println(i);
           }
        }).start();

//        Thread.currentThread().join();
    }
}
