import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N, innerCoreCnt, maxCore, minLength;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Core> coreList;
	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			maxCore = 0;
			minLength = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());
			coreList = new ArrayList<>();
			map = new char[N][N];
			for (int i=0; i<N; i++) {
				String line = br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j] = line.charAt(j<<1);

					if (map[i][j] == '1') {
						if (0==i || i==N-1 || j==0 || j==N-1) {	// 가장자리
							continue;
						}
						coreList.add(new Core(i, j));
					}
				}
			}
			innerCoreCnt = coreList.size();

			recur(0, 0, 0);

			sb.append('#').append(t).append(' ').append(minLength).append('\n');
		}
		bw.append(sb);
		bw.flush();
	}

	public static void recur(int cnt, int coreCnt, int lenSum) {
		if (cnt == innerCoreCnt) {

			// 계산 후 리턴
			if (maxCore == coreCnt) {
				minLength = Math.min(minLength, lenSum);
				return;
			}
			if (maxCore < coreCnt) {
				maxCore = coreCnt;
				minLength = lenSum;
			}
			return;
		}

		Core core = coreList.get(cnt);
		for (int w=0; w<4; w++) {
			int len = connectCore(core.r+dr[w], core.c+dc[w], 0, w);

			// 현재 코어가 연결될 수 없고, 남은 코어가 최대 코어 개수보다 적다면 탐색 의미 없음
			if (len==0 && (innerCoreCnt-cnt+coreCnt < maxCore)) {
				continue;
			}
			recur(cnt + 1, coreCnt + ((len == 0) ? 0 : 1), lenSum + len);

			disConnectCore(core.r+dr[w], core.c+dc[w], len, w);
		}
	}

	public static int connectCore(int r, int c, int len, int way) {
		if (r<0 || N<=r || c<0 || N<=c) {
			return len;
		}
		if (map[r][c] != '0') {
			return 0;
		}

		map[r][c] = '*';
		if ((len = connectCore(r + dr[way], c + dc[way], len + 1, way)) == 0) {
			map[r][c] = '0';
		}
		return len;
	}

	public static void disConnectCore(int r, int c, int len, int way) {
		if (len == 0) {
			return;
		}
		map[r][c] = '0';
		disConnectCore(r + dr[way], c + dc[way], len-1, way);
	}

	static class Core {
		int r, c;
		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
