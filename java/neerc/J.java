package neerc;

import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] kols = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = scn.next();
            for (int j = 0; j < n; j++) {
                kols[i][j] = s.charAt(j) - '0';
            }
        }
        int[][] g = new int[n][n];
        for (int q = 0; q < n; q++) {
            for (int z = q + 1; z < n; z++) {
                if (kols[q][z] == 0) {
                    continue;
                }
                g[q][z] = 1;
                for (int i = z + 1; i < n; i++) {
                    kols[q][i] = (kols[q][i] - kols[z][i] + 10) % 10;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(g[i][j]);
            }
            System.out.println();
        }
    }
}
