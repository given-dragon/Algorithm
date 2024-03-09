import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] stoneArr = new int[N+1];
        for (int i=0; i<N; i++) {
            stoneArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;
        for (int i=1; i<N; i++) {
            for (int j=0; j<i; j++) {
                if (stoneArr[i] > stoneArr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    continue;
                }
                dp[i] = Math.max(dp[i], 1);
            }
        }

        Arrays.sort(dp);
        System.out.println(dp[N-1]);
    }
}

