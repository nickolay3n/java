package com.MergeSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);}

    //сортируемый массив
    public static ArrayList<Integer> mylist = new ArrayList<Integer>();

    public static void main(String[] args) {
        //заполнение
        for (int i = 0; i < 100; i++)
            mylist.add(getRandomNumber(1,1000));

        System.out.println("Not sorted mass:");
        System.out.println(mylist);
        MergeSort(mylist);
        System.out.println("Sorted mass:");
        System.out.println(mylist);
    }

    public static ArrayList<Integer> MergeSort(ArrayList<Integer> notSortedList)
    {
        System.out.println("\n\r"+notSortedList.size());
        // Math.ceil(notSortedList.size()/2) - количество пар на первом этапе.
        List<ArrayList<Integer>> mass = new ArrayList();
        for(int i=0; i<notSortedList.size(); i++){
            ArrayList<Integer> part = new ArrayList<Integer>();
            part.add(notSortedList.get(i));
            i++;
            if(i>=notSortedList.size())
            {
                mass.add(part);
                break;
            }
            part.add(notSortedList.get(i));
            mass.add(part);
            if(part.get(0)>part.get(1)) Collections.swap(part, 0, 1);//сортируем первые корзины
        }
        // в результате List mass в котором содержатся маленькие корзины part
        // которые потом сливаются во все большие корзины
        // и количество корзин с каждым разом уменьшается
        // mass [  [part], [part], [part] ]
        //ArrayList<Integer> mass= new ArrayList<Integer>[2] ;
        //MiddleMergeSort(ArrayList<Integer> mass[])

        while(notSortedList.size()!=1)
            mass=middleMergeSort(mass);
        notSortedList= mass.get(0);
        return notSortedList;
    }


    //на входе List с отсортированными корзинами из ArrayList
    //на выходе тоже самое, сливаются по 2 шт корзины
    public static List<ArrayList<Integer>> middleMergeSort(List<ArrayList<Integer>> mass)
    {
        List<ArrayList<Integer>> sortedMass = new ArrayList();
        for (int i = 0; i < mass.size(); i++) {
            ArrayList<Integer> notSortedPart1 = mass.get(i);//получаем первую корзину
            i++;
            ArrayList<Integer> sortedPart = new ArrayList<Integer>();
            if(i < mass.size())
            {
                sortedPart=notSortedPart1;//если осталаст одна корзина то просто оставляем ее
                break;
            }
            ArrayList<Integer> notSortedPart2 = mass.get(i);//получаем вторую корзину
            //сливаем две корзины
            for (int j = 0; j < notSortedPart1.size(); j++) {
                for (int k = 0; k < notSortedPart2.size(); k++) {
                    if(notSortedPart1.get(j)<notSortedPart2.get(k))
                    {
                        sortedPart.add(notSortedPart1.get(j));
                        j++;
                    }
                    else
                    {
                        sortedPart.add(notSortedPart2.get(k));
                        k++;
                    }
                }
            }
            sortedMass.add(sortedPart);

        }
        return sortedMass;
    }
}
