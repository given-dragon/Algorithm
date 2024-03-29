import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N, W, H;
	static int[][] map;
	static int totalBlockCnt, maxBlockCnt;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			
			totalBlockCnt = 0;
			for (int i=0; i<H; i++) {
				String line = br.readLine();
				for (int j=0; j<W; j++) {
					map[i][j] = line.charAt(j<<1) - '0';
					if (map[i][j] != 0) {
						totalBlockCnt++;
					}
				}
			}
			
			maxBlockCnt = 0;
			
			recur(0, 0);
			
//			for (int i=0; i<H; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			sb.append('#').append(tc).append(' ').append(totalBlockCnt - maxBlockCnt).append('\n');
		}
		bw.append(sb);
		bw.flush();
	}
	
	public static void recur(int ballCnt, int blockCnt) {
		if (ballCnt == N) {
			maxBlockCnt = Math.max(maxBlockCnt, blockCnt);
			return;
		}

		for (int col=0; col<W; col++) {
				
			int[][] copyMap = new int[H][W];
			for (int i=0; i<H; i++) {
				copyMap[i] = Arrays.copyOf(map[i], W);
			}
			
			int cnt = bomb(col);
			gravity();
			recur(ballCnt+1, blockCnt+cnt);
			map = copyMap;
			
		}
	}
	
	public static int bomb(int col) {
		int row = 0;
		for (; row<H; row++) {
			if (map[row][col] != 0) {
				break;
			}
		}
		if (H <= row) {
			return 0;
		}
		
		int cnt = 1;
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(row, col, map[row][col]));
		map[row][col] = 0;
		
		while(!queue.isEmpty()) {
			Point target = queue.poll();
			
			for (int i=1; i<target.size; i++) {
				for (int d=0; d<4; d++) {
					int nr = target.r + dr[d]*i;
					int nc = target.c + dc[d]*i;
					
					if (nr<0 || H<=nr || nc<0 || W<=nc) {
						continue;
					}
					
					if (map[nr][nc] > 0) {
						queue.add(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0;
						cnt++;
					}
				}
			}
		}
		return cnt;
	}
	
	public static void gravity() {
		for (int j=0; j<W; j++) {
			int emptyCnt = 0;
			for (int i=H-1; i>=0; i--) {
				if (map[i][j]==0) {
					emptyCnt++;
					continue;
				}
				
				if (emptyCnt > 0) {
					map[i+emptyCnt][j] = map[i][j];
					map[i][j] = 0;	
				}
			}
		}
	}
	

	
	static class Point {
		int r, c, size;
		public Point(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
	}

}