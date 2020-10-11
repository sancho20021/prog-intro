package neerc;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int b = scn.nextInt();
        int delta = b-a;
        int n = scn.nextInt();
        System.out.println((n-b+delta-1)/delta + (n-a+delta-1)/delta);
    }
}
