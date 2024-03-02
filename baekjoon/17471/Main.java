package 이준용;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 실습041_JES_17471_게리맨더링 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static List<Integer>[] nodeInfo;
	static int[] nodeValue, nodeSaveArr;
	static int N, maxCount, total, minDiff = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		nodeInfo = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			nodeInfo[i] = new ArrayList<>();
		}
		
		maxCount = N>>1;
		nodeSaveArr = new int[(N>>1) + 1];	// 도시 조합 저장 배열
		nodeValue = new int[N+1];	// 인구 수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			nodeValue[i] = Integer.parseInt(st.nextToken());
			total += nodeValue[i];
		}
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int infoCount = Integer.parseInt(st.nextToken());
			
			while (st.hasMoreTokens()) {
				int child = Integer.parseInt(st.nextToken());
				nodeInfo[i].add(child);
				nodeInfo[child].add(i);
			}
		}
		
		nodeSaveArr[0] = 1;
		recur(0, 1);
		
		System.out.println(minDiff==Integer.MAX_VALUE ? -1 : minDiff);
	}
	
	public static void recur(int cnt, int start) {
		if (cnt > 0) {
			// 그래프 연결 체크
			int[] nodeArr1 = new int[cnt];
			for (int i=0; i<cnt; i++) {
				nodeArr1[i] = nodeSaveArr[i];
			}
			
			Arrays.sort(nodeArr1);
			int idx1=0, idx2=0;
			int[] nodeArr2 = new int[N-cnt];
			for (int i=1; i<=N; i++) {
				if (idx1<cnt && nodeArr1[idx1] == i) {
					idx1++;
					continue;
				}
				nodeArr2[idx2++] = i;
			}
			boolean conn1 = bfs(cnt, nodeArr1);
			boolean conn2 = bfs(N-cnt, nodeArr2);
			
			
			if (conn1 && conn2) {
				int g1 = 0;
				for (int idx=0; idx<cnt; idx++) {
					int selectedNode = nodeSaveArr[idx];
					g1 += nodeValue[selectedNode];
				}
				int g2 = total-g1;
				minDiff = Math.min(minDiff, Math.abs(g1-g2));
			}
		}

		if (cnt == maxCount) {	// 최대 개수에 도달하면 종료
			return;
		}
		
		for (int i=start; i<=N; i++) {
			nodeSaveArr[cnt] = i;
			recur(cnt+1, i+1);
		}
	}

	private static boolean bfs(int cnt, int[] nodeArr) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(nodeArr[0]);
		boolean[] isVisited = new boolean[101];
		isVisited[nodeArr[0]] = true;
		
		
		int nCnt = 1;
		while (!queue.isEmpty()) {
			int target = queue.poll();
			
			if (nCnt == cnt) {
				break;
			}
			
			for (int node : nodeInfo[target]) {
				if (isVisited[node]) {
					continue;
				}

				for (int idx : nodeArr) {
					isVisited[node] = true;
					if (node == idx) {
						queue.offer(node);
						nCnt++;
						break;
					}
				}
			}
		}
		return nCnt == cnt;
	}
}
