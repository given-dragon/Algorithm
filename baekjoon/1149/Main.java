import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][3];

		StringTokenizer st;
		for (int house=1; house<=N; house++) {
			st = new StringTokenizer(br.readLine());
			for (int color = 0; color<3; color++) {
				dp[house][color] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int house=1; house<=N; house++) {
			dp[house][0] += Math.min(dp[house-1][1], dp[house-1][2]);
			dp[house][1] += Math.min(dp[house-1][0], dp[house-1][2]);
			dp[house][2] += Math.min(dp[house-1][0], dp[house-1][1]);
		}
		
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}
}
