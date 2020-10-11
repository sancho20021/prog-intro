package neerc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D {
    final static int MOD = 998244353;
    static long[] pows;

    static class MathUtils {

        public static List<Integer> divisors(int x) {
            List<Integer> result = new ArrayList<>();
            result.add(1);
            for (int i = 2; i * i <= x; i++) {
                if (x % i == 0) {
                    result.add(i);
                    if (i * i != x) {
                        result.add(x / i);
                    }
                }
            }
            return result;
        }

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int k = scn.nextInt();
        pows = new long[n + 1];
        long[] d = new long[n + 1];
        long[] r = new long[n + 1];
        pows[0] = 1;
        for (int j = 1; j <= n; j++) {
            pows[j] = pows[j - 1] * k % MOD;
        }
        for (int i = 1; i <= n; i++) {
            r[i] = (int) ((i % 2 == 0 ? (long) (i / 2) * pows[i / 2] * (k + 1) : (long) i * pows[(i + 1) / 2]) % MOD);
        }
        for (int i = 1; i <= n; i++) {
            d[i] = r[i];
            List<Integer> dels = MathUtils.divisors(i);
            long kek = 0;
            for (int l : dels) {
                if (l >= i) {
                    continue;
                }
                kek += ((i / l * d[l]) % MOD + MOD) % MOD;
            }
            d[i] = (d[i] - kek) % MOD + MOD;
            d[i] %= MOD;
        }
        long res = 0;
        for (int q = 1; q <= n; q++) {
            List<Integer> dels = MathUtils.divisors(q);
            if (!dels.contains(q)) {
                dels.add(q);
            }
            for (int l : dels) {
                res = (res + d[l]) % MOD;
            }
        }
        System.out.println(res);
    }
}
