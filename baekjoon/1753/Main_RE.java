package 이준용;

import java.io.*;
import java.util.*;

public class 실습045_JES_1753_최단경로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int V, E, K;
	static List<Node>[] nodeInfo;
	static int[] minW;
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());	// 정점 수
		E = Integer.parseInt(st.nextToken());	// 간선 수
		K = Integer.parseInt(br.readLine());	// 시작 정점
		
		minW = new int[V+1];
		Arrays.fill(minW, Integer.MAX_VALUE);
		
		nodeInfo = new ArrayList[V+1];
		for (int i=1; i<=V; i++) {
			nodeInfo[i] = new ArrayList<>();
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			nodeInfo[u].add(new Node(v, w));
		}
		
		dijkstra(K);
		
		for (int i=1; i<=V; i++) {
			sb.append(minW[i] < Integer.MAX_VALUE ? minW[i] : "INF").append('\n');
		}
		
		bw.append(sb);
		bw.flush();
	}
	
	public static void dijkstra(int startV) {

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
		
		pq.offer(new Node(startV, 0));
		minW[startV] = 0;
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			
			if (minW[node.v] < node.w) {
				continue;
			}

			for (Node child : nodeInfo[node.v]) {
				int nw = minW[node.v] + child.w;
				if (nw < minW[child.v]) {
					minW[child.v] = nw;
					pq.offer(new Node(child.v, nw));
				}
			}
		}
	}

	static class Node {
		int v, w;
		public Node (int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
