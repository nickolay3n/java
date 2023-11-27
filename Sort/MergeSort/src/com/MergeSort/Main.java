package com.MergeSort;

import java.util.ArrayList;

public class Main {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);}

    //сортируемый массив
    public static ArrayList<Integer> mylist = new ArrayList<Integer>();

    public static void main(String[] args) {
        //заполнение
        for (int i = 0; i < 1000; i++)
            mylist.add(getRandomNumber(1,1000));

        System.out.println("Not sorted mass:");
        System.out.println(mylist);
        System.out.println("Sorted mass:");
        System.out.println(mylist);
    }
}
