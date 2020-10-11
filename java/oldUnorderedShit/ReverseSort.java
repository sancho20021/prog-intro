import Scanner.BetaScanner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import static checkers.IntChecker.INT_CHECKER;

public class ReverseSort {
    private static class Pair {
        long sum;
        int ind;

        public Pair(int ind, long sum) {
            this.sum = sum;
            this.ind = ind;
        }
    }

    private static class SumComp implements Comparator<Pair> {
        public int compare(Pair first, Pair second) {
            return Long.compare(first.sum, second.sum);
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[1][];
        int size = 0;
        int[] row = new int[1];
        long[] sums = new long[1];

        try {
//            BetaScanner s = new BetaScanner(System.in, "utf8", INT_CHECKER);
//            System.out.println(s.hasNextToken());
//            System.out.println(s.hasNextToken());
//            System.out.println(s.nextInt());

            BetaScanner scn = new BetaScanner(System.in, "utf8", INT_CHECKER);
            try {
                while (scn.hasInput()) {
                    int sum = 0;
                    int rowSize = 0;
                    while (!scn.isEndOfLine()) {
                        if (rowSize >= row.length) {
                            row = Arrays.copyOf(row, row.length * 2);
                        }
                        sum += row[rowSize++] = scn.nextInt();
                    }
                    if (size >= a.length) {
                        a = Arrays.copyOf(a, a.length * 2);
                        sums = Arrays.copyOf(sums, sums.length * 2);
                    }
                    Arrays.sort(a[size] = Arrays.copyOf(row, rowSize));
                    sums[size] = sum;
                    size++;
                    scn.skipLine();
                }
            } finally {
                scn.close();
            }
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
            return;
        }

        a = Arrays.copyOf(a, size);
        sums = Arrays.copyOf(sums, size);
        Pair[] pairs = new Pair[size];
        for (int i = 0; i < size; i++) {
            pairs[i] = new Pair(i, sums[i]);
        }

        Arrays.sort(pairs, new SumComp());
        for (int i = size - 1; i >= 0; i--) {
            for (int j = a[pairs[i].ind].length - 1; j >= 0; j--) {
                System.out.print(a[pairs[i].ind][j] + " ");
            }
            System.out.println();
        }
    }
}
