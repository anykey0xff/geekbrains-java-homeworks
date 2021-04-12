package ru.geekbrains.java.part3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayToListConverter<ArrayType> {
    ArrayList<ArrayType> convert (ArrayType[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
