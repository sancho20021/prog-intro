package neerc;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        double minOst = 10;
        int minOstInt = -1;
        int max = Integer.MAX_VALUE/50000;
        for(int i = 1; i<max; i++){
            if((double)i%(Math.PI*2) < minOst){
                minOst = (double)i%Math.PI;
                minOstInt = i;
            }
        }
        for(int i = -25000; i<-25000+n; i++){
            System.out.println(minOstInt*i);
        }
    }
}
