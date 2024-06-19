import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String str1, str2;

    public static void main(String[] args) throws Exception {

        str1 = "@" + br.readLine();
        str2 = "@" + br.readLine();

        int[][] dp = new int[str1.length()][str2.length()];

        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    continue;
                }

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int r = str1.length() - 1;
        int c = str2.length() - 1;
        while (0 < r || 0 < c) {
            if (str1.charAt(r) == str2.charAt(c)) {
                sb.insert(0, str1.charAt(r));
                r--;
                c--;
                continue;
            }

            if (r == 0) {
                c--;
                continue;
            } else if (c == 0) {
                r--;
                continue;
            }

            if (dp[r][c] == dp[r - 1][c]) {
                r--;
            } else {
                c--;
            }
        }

        sb.insert(0, '\n').insert(0, dp[str1.length() - 1][str2.length() - 1]);
        System.out.println(sb);
    }
}
