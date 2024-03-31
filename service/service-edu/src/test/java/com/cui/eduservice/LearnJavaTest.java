package com.cui.eduservice;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author water
 * @date 2024/3/26
 * @Description
 */
public class LearnJavaTest {
    public static BigInteger ten = BigInteger.TEN;
    public static BigInteger seven = new BigInteger("7");
    public static BigInteger a = ten.pow(9).add(seven);

    public static void main(String[] args) {
//        System.out.println(a.toString());
        Scanner scanner = new Scanner(System.in);
        BigInteger in = scanner.nextBigInteger();
        BigInteger result = g(in).remainder(a);
        System.out.println(result.toString());
    }

    public static BigInteger f(BigInteger n) {
        BigInteger sum = BigInteger.ZERO;
        n = n.add(BigInteger.ONE);
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger temp = i.pow(2);
            if (temp.compareTo(a) >= 0) {
                temp = temp.remainder(a);
            }
            sum = sum.add(temp);
            if (sum.compareTo(a) >= 0) {
                sum = sum.remainder(a);
            }
//            System.out.println(sum.toString());
        }
        return sum;
    }

    public static BigInteger g(BigInteger n) {
        BigInteger sum = BigInteger.ZERO;
        n = n.add(BigInteger.ONE);
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger temp = f(i);
            sum = sum.add(temp);
            if (sum.compareTo(a) >= 0) {
                sum = sum.remainder(a);
            }
//            System.out.println(i.toString());
        }
        return sum;
    }
}
