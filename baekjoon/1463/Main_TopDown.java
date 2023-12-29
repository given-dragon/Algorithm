import java.io.*;
import java.util.Arrays;

// dp - top down
class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N+1+2];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        System.out.println(topDown(N));
    }
    public static int topDown(int num) {
        if (dp[num] != Integer.MAX_VALUE)
            return dp[num];
        
        boolean div2 = (num&1) != 1;
        boolean div3 = (num%3) == 0;
        
        if (div3 && div2)
            dp[num] = Math.min(topDown(num / 3), topDown(num >> 1)) + 1;
        else if (div3)
            dp[num] = Math.min(topDown(num / 3), topDown(num -1)) + 1;
        else if (div2)
            dp[num] = Math.min(topDown(num >> 1), topDown(num -1)) + 1;
        else
            dp[num] = topDown(num - 1) + 1;

        return dp[num];
    }
}