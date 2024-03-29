package com.cui.eduservice;

/**
 * @author water
 * @date 2024/3/26
 * @Description
 */
public class LearnJavaTest {
    public static void main(String[] args) {
        Float a = 2.0f - 1.9f;
        Float b = 1.8f - 1.7f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999905
        System.out.println(a.equals(b));// false

    }
}
