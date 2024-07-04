import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int FIX = 1_000_000;

    static String code;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        code = "@" + br.readLine();

        int codeLen = code.length();
        dp = new int[codeLen];

        int before1 = 1;
        int before2 = 0;
        dp[before1] = 1;
        dp[before2] = 1;

        if (code.charAt(before1) == '0') {
            System.out.println(0);
            return;
        }

        for (int current = 2; current < codeLen; current++) {
            before1 = current - 1;
            before2 = current - 2;

            char currChar = code.charAt(current);
            char before1Char = code.charAt(before1);

            if (currChar == '0') {
                if (before1Char == '0' || '3' <= before1Char) {
                    System.out.println(0);
                    return;
                }

                if ('1' == before1Char || before1Char == '2') {
                    dp[current] = dp[before2];
                    continue;
                }
            }

            if (before1Char == '1' &&
                    '1' <= currChar && currChar <= '9') {
                dp[current] = (dp[before2] + dp[before1]) % FIX;
                continue;
            }

            if (before1Char == '2' &&
                '1' <= currChar && currChar <= '6') {
                dp[current] = (dp[before2] + dp[before1]) % FIX;
                continue;
            }

            dp[current] = dp[before1];
        }

        System.out.println(dp[codeLen-1]);
    }
}
