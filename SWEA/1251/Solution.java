import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static List<Vertex>[] nodeInfo;
	static boolean[] visited;
	static double[] minDist;
	public static void main(String[] args) throws Exception {
		int C = Integer.parseInt(br.readLine());
		for (int c=1; c<=C; c++) {
			
			int N = Integer.parseInt(br.readLine());	// 섬의 개수
			
			StringTokenizer stX = new StringTokenizer(br.readLine());
			StringTokenizer stY = new StringTokenizer(br.readLine());
			int[][] coordArr = new int[N][N];
			for (int i=0; i<N; i++) {
				int x = Integer.parseInt(stX.nextToken());
				int y = Integer.parseInt(stY.nextToken());
				coordArr[i] = new int[] {x, y};
			}
			
			double E = Double.parseDouble(br.readLine());
			visited = new boolean[N];
			minDist = new double[N];
			Arrays.fill(minDist, Double.MAX_VALUE);
			nodeInfo = new ArrayList[N];
			for (int i=0; i<N; i++) {
				nodeInfo[i] = new ArrayList<>();
			}
			for (int i=0; i<N; i++) {
				for (int j=i+1; j<N; j++) {
					double weight = 
							E * (Math.pow(coordArr[i][0] - coordArr[j][0], 2) +
									Math.pow(coordArr[i][1] - coordArr[j][1], 2));
					
					nodeInfo[i].add(new Vertex(j, weight));
					nodeInfo[j].add(new Vertex(i, weight));
				}
			}
			
			PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> v.w));
			pq.add(new Vertex(0, 0));
			minDist[0] = 0;
			
			int eCnt = 0;
			double totalWeight = 0;
			while (!pq.isEmpty()) {
				Vertex target = pq.poll();
				if (visited[target.no]) {
					continue;
				}
				
				totalWeight += target.w;
				visited[target.no] = true;
				if (++eCnt==N) {
					break;
				}
				
				for (Vertex v : nodeInfo[target.no]) {
					if (visited[v.no]) {
						continue;
					}
					
					if (v.w < minDist[v.no]) {
						minDist[v.no] = v.w;
						pq.offer(v);
					}
				}
			}
			
			sb.append('#').append(c).append(' ').append(Math.round(totalWeight)).append('\n');
		}
		
		bw.append(sb);
		bw.flush();
	}
	
	static class Vertex {
		int no;
		double w;
		public Vertex(int no, double w) {
			this.no = no;
			this.w = w;
		}
	}
}
