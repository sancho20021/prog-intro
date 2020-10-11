package neerc;

public class test {
    final static int MOD = 998244353;

    public static void main(String[] args) {
        long res = 0;
        System.out.println((((long) Math.pow(7, 1)) % MOD + MOD)% MOD);
        res = (res +
                (((long) Math.pow(7, 1)) % MOD + MOD) % MOD *
                        (((long) Math.pow(7, 1)) % MOD
                                + MOD) % MOD
        ) % MOD;
        System.out.println(res);
    }
}
