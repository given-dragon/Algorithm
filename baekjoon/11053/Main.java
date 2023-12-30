import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        String[] strNums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(strNums[i]);
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (seq[i] > seq[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int result = Arrays.stream(dp).max().getAsInt();
        System.out.println(result);
    }
}