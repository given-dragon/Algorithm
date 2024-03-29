import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static App[] appArr;
	public static void main(String[] args) throws Exception {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 앱 개수
		M = Integer.parseInt(st.nextToken());	// 확보해야 하는 메모리
		
		StringTokenizer memSt = new StringTokenizer(br.readLine());
		StringTokenizer costSt = new StringTokenizer(br.readLine());
		appArr = new App[N];
		for (int i=0; i<N; i++) {
			appArr[i] = new App(
					Integer.parseInt(memSt.nextToken()),
					Integer.parseInt(costSt.nextToken())
					);
		}
		
		int costSum = 0;
		for (int i=0; i<N; i++) {
			costSum += appArr[i].cost;
		}
		
		int[] dp = new int[costSum+1];	// [코스트] == 최대 바이트
		for (int i=0; i<N; i++) {
			for (int cost=costSum; cost>=0; cost--) {
				if (cost < appArr[i].cost) {
					continue;
				}
				dp[cost] = Math.max(dp[cost], 
						dp[cost - appArr[i].cost] + appArr[i].byteSize);
			}
		}
		
		for (int i=0; i<=costSum; i++) {
			if (dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
	
	static class App {
		int byteSize, cost;
		public App (int byteSize, int cost) {
			this.byteSize = byteSize;
			this.cost = cost;
		}
	}
}
