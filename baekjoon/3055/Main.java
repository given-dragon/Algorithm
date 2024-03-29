import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int R, C;
	static int[][] map, waterMap;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static Point start, end;
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		waterMap = new int[R][C];
		for (int i=0; i<R; i++) {
			Arrays.fill(waterMap[i], Integer.MAX_VALUE);
		}
		
		PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.t));
		for (int i=0; i<R; i++) {
			String line = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '*') {
					pq.add(new Point(i, j, 0));
					waterMap[i][j] = 0;
					continue;
				}
				if (map[i][j] == 'S') {
					start = new Point(i, j, 0);
					continue;
				}
				if (map[i][j] == 'D') {
					end = new Point(i, j, 0);
				}
			}
		}
		
		while(!pq.isEmpty()) {
			Point target = pq.poll();
			
			if (waterMap[target.r][target.c]  < target.t) {
				continue;
			}
			
			for (int d=0; d<4; d++) {
				int nr = target.r + dr[d];
				int nc = target.c + dc[d];
				if (nr<0 || R<=nr || nc<0 || C<=nc || map[nr][nc]=='X' || map[nr][nc]=='D') {
					continue;
				}
				if (waterMap[nr][nc] > target.t+1) {
					waterMap[nr][nc] = target.t+1;
					pq.add(new Point(nr, nc, waterMap[nr][nc]));
				}
			}
		}
		
		String answer = "KAKTUS";
		pq.add(start);
		while(!pq.isEmpty()) {
			Point target = pq.poll();
			
			if (waterMap[target.r][target.c]  < target.t) {
				continue;
			}
			
			if (target.r == end.r && target.c==end.c) {
				answer = String.valueOf(target.t);
				break;
			}
			
			for (int d=0; d<4; d++) {
				int nr = target.r + dr[d];
				int nc = target.c + dc[d];
				if (nr<0 || R<=nr || nc<0 || C<=nc || map[nr][nc]=='X') {
					continue;
				}
				if (waterMap[nr][nc] > target.t+1) {
					waterMap[nr][nc] = target.t+1;
					pq.add(new Point(nr, nc, target.t+1));
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static class Point {
		int r, c, t;
		public Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
