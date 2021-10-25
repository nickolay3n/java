package sample;

import java.util.ArrayList;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class MySort {
    public static ArrayList<Integer> myArrayList = new ArrayList();

    //public ArrayList<Integer> myGetArray(int myLength)
    public static void myGetArray(int myLength)
        {
            myArrayList.clear();
            while(myLength!=0) {
                myArrayList.add(myRand());
                myLength--;
            }
            //return myArrayList;
        }

        public static int myRand()     { return (int) round(random()*1000); }

    public static String printArray(int myLength) {
        myGetArray(myLength);/*
        for(Object i : myArrayList) {
            String str = i.toString();
            Controller.TextArrayID.setText(Controller.TextArrayID.getText()+" "+ str);
        }*/
        return myArrayList.toString();
    }
}
