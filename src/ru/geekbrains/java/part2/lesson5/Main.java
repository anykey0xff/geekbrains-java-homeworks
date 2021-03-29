package ru.geekbrains.java.part2.lesson5;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = 5000000;

    public static void main(String[] args) {
        ArrayMethod01();
        ArrayMethod02();
    }

    static void ArrayMethod01() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1f);
        long startTime = System.currentTimeMillis();
        calculateValues(arr);
        long finishTime = System.currentTimeMillis();
        System.out.println("Method 1 time (ms): " + (finishTime - startTime));
    }

    static void ArrayMethod02() {

        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        Arrays.fill(arr, 1f);

        long startTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(() -> calculateValues(arr1));
        Thread t2 = new Thread(() -> calculateValues(arr2));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        long finishTime = System.currentTimeMillis();
        System.out.println("Method 2 time (ms): " + (finishTime - startTime));
    }

    static void calculateValues(float[] values) {
        for(int i = 0; i < values.length; ++i) {
            values[i] = (float)(values[i] * Math.sin((0.2f + i / 5f)) * Math.cos((0.2f + i / 5f)) * Math.cos((0.4f + i / 2f)));
        }
    }
}
