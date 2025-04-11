package com.umiitkose.events.example.performace;

import java.util.List;

import static com.umiitkose.events.data.Data.generateIntegerLists;

public class Performance {

    void main() {
        List<List<Integer>> intList = generateIntegerLists();
        List<Integer> millionData = intList.getFirst();
        List<Integer> thousandData = intList.get(1);
        List<Integer> tenThousandData = intList.getLast();

        long l = System.currentTimeMillis();
        int sum = 0;
        for (int i : millionData) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(sum / millionData.size());
        long l2 = System.currentTimeMillis();
        System.out.println("0 : " + (l2 - l));

        long l3 = System.currentTimeMillis();
        System.out.println("Toplam: " + millionData.stream().mapToInt(Integer::intValue).sum());
        long l4 = System.currentTimeMillis();
        System.out.println("1 : " + (l4 - l3));

        long l5 = System.currentTimeMillis();
        System.out.println("Ortalama: " + millionData.stream().mapToInt(Integer::intValue).average().orElse(0));
        long l6 = System.currentTimeMillis();
        System.out.println("2 : " + (l6 - l5));
    }
}
