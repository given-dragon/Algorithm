import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N, M;
	static List<Integer>[] adjList;
	static List<Integer>[] adjListRv;
	static int[] connCnt;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[N+1];
			adjListRv = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<>();
				adjListRv[i] = new ArrayList<>();
			}
			
			StringTokenizer st;
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjListRv[b].add(a);
			}
			
			int ans = 0;
			connCnt = new int[N+1];
			for (int i=1; i<=N; i++) {
				connCnt[i]++;
				findConnNode(adjList, i);
				findConnNode(adjListRv, i);
				if (connCnt[i] == N) {
					ans++;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.append(sb);
		bw.flush();
	}
	
	public static void findConnNode(List<Integer>[] adjList, int startNode) {
		visited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(startNode);
		visited[startNode] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			
			for (int child : adjList[node]) {
				if (visited[child]) {
					continue;
				}
				queue.add(child);
				visited[child] = true;
				connCnt[startNode]++;
			}
		}
	}
}
