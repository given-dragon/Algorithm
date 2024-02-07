package 이준용;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 실습019_SWEA_1861_정사각형방 {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxCount, N, roomNum;
	static int[][] roomArr, moveArr;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			moveArr = new int[N][N];
			roomArr = new int[N][N];
			for (int i=0; i<N; i++) {
				String[] split = br.readLine().split(" ");
				for (int j=0; j<N; j++) {
					roomArr[i][j] = Integer.parseInt(split[j]); 
				}
			}

			maxCount = 0;
			roomNum=Integer.MAX_VALUE;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					int count = dfs(i, j);
					if (maxCount > count) {
						continue;
					}
					roomNum = (maxCount == count) ? Math.min(roomNum, roomArr[i][j]) : roomArr[i][j];
					maxCount = count;
				}
			}

			sb.append('#').append(test_case).append(' ').append(roomNum).append(' ').append(maxCount).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	public static int dfs(int r, int c) {
		if (moveArr[r][c] != 0) {
			return moveArr[r][c];
		}

		int count = 1;
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || N <= nr || nc < 0 || N <= nc) {
				continue;
			}
			if (roomArr[r][c]+1 != roomArr[nr][nc]) {
				continue;
			}

			count += dfs(nr, nc);
		}
		moveArr[r][c] = count;
		return moveArr[r][c];
	}
}

