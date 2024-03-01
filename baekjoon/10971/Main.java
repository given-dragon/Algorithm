import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, startVtx, minWeightSum=Integer.MAX_VALUE;
	static int[][] adj;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		adj = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<N; i++) {
			visited = new boolean[N];
			startVtx = i;
			
			visited[i] = true;
			recur(1, i, 0);
			visited[i] = false;
		}
		
		System.out.println(minWeightSum);
	}

	public static void recur(int idx, int from, int weightSum) {
		if (minWeightSum <= weightSum) {
			return;
		}
		
		if (idx == N) {
			if (adj[from][startVtx] == 0) {
				return;
			}
			minWeightSum = Math.min(minWeightSum, weightSum+adj[from][startVtx]);
			return;
		}
		
		for (int to=0; to<N; to++) {
			if (visited[to] || adj[from][to]==0) {
				continue;
			}
			
			visited[to] = true;
			recur(idx+1, to, weightSum+adj[from][to]);
			visited[to] = false;
		}
	}
}
