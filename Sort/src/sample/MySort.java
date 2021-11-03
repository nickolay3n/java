package sample;

import java.util.ArrayList;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class MySort {
    public static Integer[] mySwap(Integer a, Integer b) {
        a = a + b;
        b = a - b;
        a = a - b;
        return new Integer[]{a, b};
    }

    public static ArrayList<Integer> myArrayList = new ArrayList();
    public static ArrayList<Integer> myArrayListSorted = new ArrayList();

    public static void myGetArray(int myLength) {
        myArrayList.clear();
        while (myLength != 0) {
            myArrayList.add((int) round(random() * 1000));
            myLength--;
        }
    }

    public static String printArray(int myLength) {
        myGetArray(myLength);
        return myArrayList.toString();
    }

    public static String sort(String mySortType) {
        myArrayListSorted = (ArrayList<Integer>) myArrayList.clone();
        switch (mySortType) {
            case ("Сортировка пузырьком"):
                return Sort_Bubble(myArrayList).toString() + "\nХудшее время О(n^2)     Затраты памяти O(1)";
            case ("Type To Sort"):
                return "Выберете тип сортировки";
            default:
                return "что то пошло не так";
        }
    }

    //СОРТИРОВКА ПУЗЫРЬКОМ Худшее время О(n^2)     Затраты памяти O(1)
    public static ArrayList<Integer> Sort_Bubble(ArrayList<Integer> arrList) {
        int n = arrList.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arrList.get(j) > arrList.get(j + 1)) {
                    Integer temp = arrList.get(j);
                    arrList.set(j, arrList.get(j + 1));
                    arrList.set((j + 1), temp);
                }
        return arrList;
    }

    public static int[] Sort_Bubble(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }


}
