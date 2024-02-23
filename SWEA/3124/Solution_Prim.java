import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    
    static List<Vertex>[] nodeInfo;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());    // 테스트케이스 수 입력받기
        for (int t = 1; t <= T; t++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int V = Integer.parseInt(st.nextToken());
        	int E = Integer.parseInt(st.nextToken());
        	
        	nodeInfo = new ArrayList[V+1];
        	visited = new boolean[V+1];
        	while(E-- > 0) {
        		st = new StringTokenizer(br.readLine());
        		int v1 = Integer.parseInt(st.nextToken());
        		int v2 = Integer.parseInt(st.nextToken());
        		int w = Integer.parseInt(st.nextToken());
        		
        		if (nodeInfo[v1] == null) {
        			nodeInfo[v1] = new ArrayList<>();
        		}
        		if (nodeInfo[v2] == null) {
        			nodeInfo[v2] = new ArrayList<>();
        		}
        		
        		nodeInfo[v1].add(new Vertex(v2, w));
        		nodeInfo[v2].add(new Vertex(v1, w));
        	}
        	
        	int[] minDist = new int[V+1];
        	Arrays.fill(minDist, Integer.MAX_VALUE);
        	minDist[1] = 0; 
        	PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v->v.w));
        	pq.offer(new Vertex(1, 0));
        	
        	long totalWeight = 0;
        	int eCnt = 0;
        	while(!pq.isEmpty()) {
        		
        		Vertex target = pq.poll();
        		if (visited[target.no]) {
        			continue;
        		}
        		
        		totalWeight += target.w;
        		visited[target.no] = true;
        		if (++eCnt == V) {
        			break;
        		}
        		for (Vertex v :  nodeInfo[target.no]) {
        			if (visited[v.no]) {
        				continue;
        			}
        			
        			if (v.w < minDist[v.no]) {
        				minDist[v.no] = v.w;
        				pq.offer(v);
        			}
        		}
        	}
        	
        	sb.append('#').append(t).append(' ').append(totalWeight).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }

    static class Vertex {
        int no, w;
        public Vertex(int no, int w) {
            this.no = no;
            this.w = w;
        }
    }
}