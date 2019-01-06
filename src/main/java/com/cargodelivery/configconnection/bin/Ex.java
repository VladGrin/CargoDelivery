package com.cargodelivery.configconnection.bin;

import java.util.Set;
import java.util.TreeSet;

public class Ex {
    public static void main(String[] args) {
        Set<String> stringSet = new TreeSet<>();
        stringSet.add("aaa");
        stringSet.add("ddd");
        stringSet.add("ccc");
        stringSet.add("vvv");
//        String s = stringSet.stream().filter(x -> x.equals("aaa")).findFirst().orElseThrow()
//        System.out.println(s);
    }
}
