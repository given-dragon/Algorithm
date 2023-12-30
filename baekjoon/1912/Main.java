import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        String[] strNums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            dp[i] = Integer.parseInt(strNums[i]);
        }

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + dp[i]);
        }

        int result = Arrays.stream(dp).max().getAsInt();
        System.out.println(result);
    }
}