import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");
        int[] seq = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(split[i]);
            dp[i] = seq[i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (seq[i] > seq[j]) {
                    dp[i] = Math.max(dp[j] + seq[i], dp[i]);
                }
            }
        }

        int result = Arrays.stream(dp).max().getAsInt();
        System.out.println(result);
    }

}