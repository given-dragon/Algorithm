import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[][] coinMap, moveMap;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int size;
	public static void main(String[] args) throws Exception {
		
		int pNum = 1;
		while ((size = Integer.parseInt(br.readLine())) != 0) {
			coinMap = new int[size][size];
			moveMap = new int[size][size];
			
			StringTokenizer st;
			for (int i=0; i<size; i++) {
				Arrays.fill(moveMap[i], Integer.MAX_VALUE);
				
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<size; j++) {
					coinMap[i][j] = st.nextToken().charAt(0) - '0';
				}
			}
			
			sb.append("Problem ").append(pNum++).append(": ")
			.append(findResult()).append('\n');
		}
		
		bw.append(sb);
		bw.flush();
	}
	
	public static int findResult() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(0, 0, coinMap[0][0]));
		moveMap[0][0] = coinMap[0][0];
		
		while (!queue.isEmpty()) {
			Point target = queue.poll();
			
			for (int d=0; d<4; d++) {
				int nr = target.r + dr[d];
				int nc = target.c + dc[d];
				
				if (nr<0 || size<=nr || nc<0 || size<=nc) {
					continue;
				}
				if (moveMap[nr][nc] <= moveMap[target.r][target.c] + coinMap[nr][nc]) {
					continue;
				}
				
				moveMap[nr][nc] = moveMap[target.r][target.c] + coinMap[nr][nc];
				queue.add(new Point(nr, nc, moveMap[nr][nc]));
			}
		}
		
		return moveMap[size-1][size-1];
	}
	
	static class Point {
		int r, c, coinCnt;
		public Point(int r, int c, int coinCnt) {
			this.r = r;
			this.c = c;
			this.coinCnt = coinCnt;
		}
	}
}

