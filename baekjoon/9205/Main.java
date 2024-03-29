import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int n, m;
	static Point[] pointArr;
	static Point home, dest;
	static StringTokenizer st;

	static List<Node>[] adjList;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());	// 편의점 개수
			m = n+2;	// 노드 개수
			
			adjList = new ArrayList[m];
			for (int i=0; i<m; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			pointArr = new Point[m];
			pointArr[0] = getPoint(); // home
			for (int i=2; i<m; i++) {
				pointArr[i] = getPoint();
			}
			pointArr[1] = getPoint(); // dest
			
			setAdjList();
			
			visited = new boolean[m];
			Queue<Node> queue = new ArrayDeque<>();
			queue.add(new Node(0, 0)) ;
			
			String answer = "sad";
			while(!queue.isEmpty()) {
				Node target = queue.poll();
				
				if (target.no == 1) {
					answer = "happy";
					break;
				}
				
				for (Node child : adjList[target.no]) {
					if (visited[child.no]) {
						continue;
					}
					
					queue.add(child);
					visited[child.no] = true;
				}
			}
			
			sb.append(answer).append('\n');
		}
		bw.append(sb);
		bw.flush();
	}

	public static void setAdjList() {
		for (int i=0; i<m; i++) {
			for (int j=i+1; j<m; j++) {
				int dist = getDist(pointArr[i], pointArr[j]);
				if (dist <= 1000) {
					adjList[i].add(new Node(j, dist));
					adjList[j].add(new Node(i, dist));	
				}
			}
		}
	}
	
	public static int getDist(Point s, Point e) {
		return Math.abs(s.r-e.r) + Math.abs(s.c-e.c);
	}
	
	public static Point getPoint() throws Exception{
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		return new Point(r, c);
	}
	
	static class Node {
		int no, w;
		public Node(int no, int w) {
			super();
			this.no = no;
			this.w = w;
		}
	}
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
