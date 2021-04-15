package ru.geekbrains.java.part3.lesson1;

public class ElementsSwapper<T> {
    void swap (T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
