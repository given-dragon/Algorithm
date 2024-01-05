import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int C = Integer.parseInt(split[0]); // 최소 인원
        int N = Integer.parseInt(split[1]); // 도시 개수

        int[] people = new int[N];
        int[] exp = new int[N];

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            exp[i] = Integer.parseInt(split[0]);
            people[i] = Integer.parseInt(split[1]);
        }

        int[][] dp = new int[N][C + 1];
        for (int[] temp : dp) {
            Arrays.fill(temp, 10);
        }
        for (int i = 0; i < N; i++) {
            dp[i][1] = exp[i];
        }

        for (int i = 2; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                int before = i - people[j];
                if (before <= 0) {
                    dp[j][i] = dp[j][i - 1];
                    continue;
                }
                int minVal = getMinVal(N, dp, before);
                dp[j][i] = minVal + exp[j];
            }
        }
        System.out.println(getMinVal(N, dp, C));
    }

    private static int getMinVal(int N, int[][] dp, int C) {
        int minVal = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++) {
            minVal = Math.min(minVal, dp[k][C]);
        }
        return minVal;
    }
}