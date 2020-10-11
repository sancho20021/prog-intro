package neerc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        for (int q = 0; q < t; q++) {
            int n = scn.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scn.nextInt();
            }
            Map<Integer, Integer> map = new HashMap<>();
            map.put(a[n - 1], map.getOrDefault(a[n - 1], 0) + 1);
            int res = 0;
            for (int j = n - 2; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    int difK = 2 * a[j] - a[i];
                    res+=map.getOrDefault(difK, 0);
                }
                map.put(a[j], map.getOrDefault(a[j], 0) + 1);
            }
            System.out.println(res);
        }
    }
}
