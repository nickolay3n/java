package com.MergeSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



//*** СОРТИРОВКА СЛИЯНИЕМ ***
// Сложность данного алгоритма скромна — O(n2)
//Два отсортированных подмассива можно объединить за время O(n)
//Повторяем, пока не закончатся все элементы. 
//Сортировка слиянием требует времени O(nlog n) для всех случаев
//Пока мы делим массивы на половины, на каждом уровне рекурсии получаем log n. Затем, для слияния подмассивов нужно всего n сравнений. Следовательно, O(nlog n)

public class Main {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);}

    //сортируемый массив
    public static ArrayList<Integer> mylist = new ArrayList<Integer>();

    public static void main(String[] args) {
        //заполнение
        for (int i = 0; i < 100; i++)
            mylist.add(getRandomNumber(1,1000));
        mylist.add(10,null);

        System.out.println("Not sorted mass:");
        System.out.println(mylist);
        System.out.println("\n\r"+mylist.size());
        mylist = MergeSort(mylist);
        System.out.println("Sorted mass:");
        System.out.println(mylist);
        System.out.println("\n\r"+mylist.size());
    }

    public static ArrayList<Integer> MergeSort(ArrayList<Integer> notSortedList)
    {
        //удаляем все null элементы
        int nullCountVariables=0;
        for(int i=0; i<notSortedList.size(); i++){
            if(notSortedList.get(i)==null)
            {
                notSortedList.remove(i);
                nullCountVariables++;
            }
        }
        //удаляем все null элементы------

        // Math.ceil(notSortedList.size()/2) - количество пар на первом этапе.
        List<ArrayList<Integer>> mass = new ArrayList();//массив корзин
        for(int i=0; i<notSortedList.size(); i++){
            ArrayList<Integer> part = new ArrayList<Integer>();//создаем корзину
            part.add(notSortedList.get(i));
            i++;
            if(i>=notSortedList.size())
            {
                mass.add(part);
                break;
            }
            part.add(notSortedList.get(i));
            if(part.get(0)>part.get(1)) Collections.swap(part, 0, 1);//сортируем первые корзины
            mass.add(part);//добавляем корзину в массив корзин

        }
        // в результате List mass в котором содержатся маленькие корзины part
        // которые потом сливаются во все большие корзины
        // и количество корзин с каждым разом уменьшается
        // mass [  [part], [part], [part] ]

        while(mass.size()!=1)
        {
            mass=middleMergeSort(mass);

        }
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
            if(i >= mass.size())
            {
                sortedPart=notSortedPart1;//если осталаст одна корзина то просто оставляем ее
                sortedMass.add(sortedPart);
                break;
            }
            ArrayList<Integer> notSortedPart2 = mass.get(i);//получаем вторую корзину
            //сливаем две корзины
            while(true)
            {
                if(notSortedPart2.size()==0 && notSortedPart1.size()==0) break;
                if(notSortedPart2.size()==0 && notSortedPart1.size()!=0)
                {
                    sortedPart.add(notSortedPart1.get(0));
                    notSortedPart1.remove(0);
                    continue;
                }
                if(notSortedPart2.size()!=0 && notSortedPart1.size()==0)
                {
                    sortedPart.add(notSortedPart2.get(0));
                    notSortedPart2.remove(0);
                    continue;
                }
                if(notSortedPart2.size()!=0 && notSortedPart1.size()!=0)
                {
                    if(notSortedPart1.get(0)<=notSortedPart2.get(0)) //сравнение при слиянии корзин
                    {
                        sortedPart.add(notSortedPart1.get(0));
                        notSortedPart1.remove(0);
                        continue;
                    }
                    else
                    {
                        sortedPart.add(notSortedPart2.get(0));
                        notSortedPart2.remove(0);
                        continue;
                    }
                }
            }
            sortedMass.add(sortedPart);

        }
        return sortedMass;
    }
}
