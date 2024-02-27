package 이준용;

import java.io.*;
import java.util.Arrays;

public class 실습048_JES_1463_1로만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1+2];
		dp[0] = 0; dp[1] = 0;
		dp[2] = 1; dp[3] = 1;
		
		for (int i=4; i<=N; i++) {
			dp[i] = dp[i-1];
			if ((i & 1) != 1) {
				dp[i] = Math.min(dp[i], dp[i>>1]);
			}
			if (i%3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3]);
			}
			dp[i]++;
		}
		
		System.out.println(dp[N]);
	}
}
