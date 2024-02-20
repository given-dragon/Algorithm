import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] nodeInfo;
	static int[] eCount;
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nodeInfo = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			nodeInfo[i] = new ArrayList<>();
		}
		
		eCount = new int[N+1];
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());

			nodeInfo[front].add(back);
			eCount[back]++;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (eCount[i] == 0) {
				queue.add(i);
			}
		}
		bfs(queue);

		bw.append(sb);
		bw.flush();
	}
	
	public static void bfs(Queue<Integer> queue) {
		while (!queue.isEmpty()) {
			int target = queue.poll();
			sb.append(target).append(' ');
			
			for (int node : nodeInfo[target]) {
				if (--eCount[node] == 0) {
					queue.add(node);
				}
			}
		}
	}
}
