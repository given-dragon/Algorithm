import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[] nodeArr;

	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			nodeArr = new int[n+1];
			for (int i=1; i<=n; i++) {
				nodeArr[i] = i;
			}
			
			sb.append('#').append(t).append(' ');
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				
				char cmd = st.nextToken().charAt(0);
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());		
				
				if (cmd == '0') {
					union(node1, node2);
					continue;
				}
				
				sb.append(isSameGroup(node1, node2) ? '1' : '0');
			}
			sb.append('\n');
		}
		bw.append(sb);
		bw.flush();
	}
	
	public static boolean isSameGroup(int node1, int node2) {
		return find(node1) == find(node2);
	}
	
	public static void union(int node1, int node2) {
		int n1 = find(node1);
		int n2 = find(node2);
		
		nodeArr[n2] = n1;
	}
	
	public static int find(int node) {
		if (nodeArr[node] == node) {
			return node;
		}
		
		nodeArr[node] = find(nodeArr[node]);
		return nodeArr[node]; 
	}
}
