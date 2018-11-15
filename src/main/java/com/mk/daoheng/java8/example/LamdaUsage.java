package com.mk.daoheng.java8.example;

public class LamdaUsage {

    @FunctionalInterface
    private interface Adder{
        int add(int a,int b);
    }

    public static void main(String[] args) {
        //Predicate boolean test(T t)    用于判断
        //Consumer accept(T t)  get一个T 不返回
        //Function<T,R> apply<T t>  给一个T返回一个R
        //Supplier<T> T get();  不给参数，返回一个T

        //  bi consumer接受两个参数

        Runnable t1 = ()-> System.out.println(Thread.currentThread());
        process(t1);
    }

    public static void process(Runnable r){
        r.run();
    }
}
