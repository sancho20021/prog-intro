package neerc;

import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int xl = Integer.MAX_VALUE, xr = Integer.MIN_VALUE, yd = Integer.MAX_VALUE, yu = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            int x = scn.nextInt(), y = scn.nextInt(), h = scn.nextInt();
            xl = Math.min(xl, x-h);
            xr = Math.max(xr, x+h);
            yd = Math.min(yd, y-h);
            yu = Math.max(yu, y+h);
        }
        int x = (xl+xr)/2;
        int y = (yd+yu)/2;
        int h = (Math.max(xr-xl, yu-yd)+1)/2;
        System.out.println(x + " " + y + " " + h);
    }
}
