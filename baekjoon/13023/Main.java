import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<Integer>[] nodeInfo;
	static boolean[] isVisited;
	static boolean result;

	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 사람 수
		int M = Integer.parseInt(st.nextToken());	// 친구관계 수
		
		nodeInfo = new ArrayList[N];
		for (int i=0; i<N; i++) {
			nodeInfo[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodeInfo[a].add(b);
			nodeInfo[b].add(a);
		}
		
		isVisited = new boolean[N];
		for (int i=0; i<N; i++) {
			isVisited[i] = true;
			recur(1, i);
			isVisited[i] = false;
			
			if (result) {
				break;
			}
		}
		bw.append(result ? '1' : '0');
		bw.flush();
	}
	
	public static void recur(int cnt, int node) {
		if (cnt >= 5) {
			result = true;
			return;
		}
		
		for (int child : nodeInfo[node]) {
			if (isVisited[child]) {
				continue;
			}
			
			isVisited[child] = true;
			recur(cnt+1, child);
			isVisited[child] = false;
		}
	}
}
