package Prog14;

import java.util.Arrays;
import java.util.Random;

public class Prog14_03
{
    public static void main(String[] args)
    {
        new Prog14_03();
    }

    public Prog14_03()
    {
        Random rnd = new Random();

        int[] list = new int[10];
        fillArray(list);
        printArray(list);
        Arrays.sort(list);
        printArray(list);

        int x = rnd.nextInt(10);
        System.out.println("x="+ x);
        System.out.println(locateLeftEnd(list, 0, list.length-1, x));
    }

    public void fillArray(int[] list)
    {
        Random rnd = new Random();
        for(int i=0; i <list.length; i++)
        {
            list[i] = rnd.nextInt(10);
        }
    }

    public void printArray(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    public boolean binarySearch(int[] list, int x)
    {
        return binarySearch(list, 0, list.length-1, x);
    }

    private boolean binarySearch(int[] list, int first, int last, int x)
    {
        boolean found;

        if (first > last) found = false;
        else
        {
            int mid = (first + last)/2;
            if(list[mid]  == x) found = true;
            else
            if(x < list[mid])
                found = binarySearch(list, first, mid-1,x);
            else
                found = binarySearch(list, mid+1,last,x);
        }
        return found;
    }

    private int locateLeftEnd(int[] list, int first, int last, int x){
        int loc;

        if (first > last) loc = first;
        else {
            int mid = (first+last)/2;
            //System.out.print(mid + " ");
            if (x <= list[mid])
                loc = locateLeftEnd(list, first, mid-1, x);
            else
                loc = locateLeftEnd(list, mid+1, last, x);
        }
        return loc;
    }
}