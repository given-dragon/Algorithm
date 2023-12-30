import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

class Main {
    static BigInteger[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        dp = new BigInteger[n+1][m+1];
        for (BigInteger[] bigIntegers : dp) {
            Arrays.fill(bigIntegers, BigInteger.ZERO);
        }

        System.out.println(combination(n, m));
    }

    public static BigInteger combination(int n, int m) {
        if (m < 1 || n < 1)
            return BigInteger.ONE;
        if (!dp[n][m].equals(BigInteger.ZERO))
            return dp[n][m];
        if (n==m)
            return BigInteger.ONE;

        dp[n][m] = combination(n - 1, m - 1).add(combination(n - 1, m));
        return dp[n][m];
    }
}
// 100 C 50은 100,891,344,545,564,193,334,812,497,256로 수가 너무커 long타입으로 커버 불가능
// long 범위 : -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807 (8바이트)