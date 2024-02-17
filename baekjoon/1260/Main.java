import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] nodeInfo;
	static boolean[] isVisited;
	static int N, M, V;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 정점 개수
		M = Integer.parseInt(st.nextToken());	// 간선 개수
		V = Integer.parseInt(st.nextToken());	// 탐색 시작 번호
		
		nodeInfo = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			nodeInfo[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			nodeInfo[v1].add(v2);
			nodeInfo[v2].add(v1);
		}
		for (int i=1; i<=N; i++) {
			nodeInfo[i].sort(Comparator.naturalOrder());
		}
		
		doDfs(V);
		sb.append('\n');

		doBfs(V);
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	public static void doDfs(int startV) {
		isVisited = new boolean[N+1];
		isVisited[startV] = true;
		dfs(startV);
	}
	

	public static void dfs(int v) {
		sb.append(v).append(' ');
		
		for (int nearV : nodeInfo[v]) {
			if (isVisited[nearV]) {
				continue;
			}
			
			isVisited[nearV] = true;
			dfs(nearV);
		}
	}
	
	public static void doBfs(int startV) {
		Queue<Integer> q = new ArrayDeque<>();
		isVisited = new boolean[N+1];
		
		q.add(startV);
		isVisited[startV] = true;
		
		while (!q.isEmpty()) {
			int targetV = q.poll();
			sb.append(targetV).append(' ');
			
			for (int nearV : nodeInfo[targetV]) {
				if (isVisited[nearV]) {
					continue;
				}
				
				isVisited[nearV] = true;
				q.add(nearV);
			}
		}
	}
}
