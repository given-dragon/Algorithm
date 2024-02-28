import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int K, W, H;
	static char[][] map;
	static int[][][] moveMap;
	static int[] dr = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2,  2,  1};
	static int[] dc = {0, 0, -1, 1, -2, -1,  1,  2, 2, 1, -1, -2};

	public static void main(String[] args) throws Exception {
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H+1][W+1];
		for (int i=1; i<=H; i++) {
			String line = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j+1] = line.charAt(j<<1);
			}
		}
		
		moveMap = new int[K+1][H+1][W+1];
		for (int[][] dm : moveMap) {
			for (int[] r : dm) {
				Arrays.fill(r, Integer.MAX_VALUE);
			}
		}
		for (int i=0; i<=K; i++) {
			moveMap[i][1][1] = 0;
		}
		
		Queue<Coord> queue = new ArrayDeque<>();
		queue.add(new Coord(1, 1, 0, 0));
		
		while (!queue.isEmpty()) {
			Coord c = queue.poll();
			if (c.r == H && c.c == W) {
				break;
			}
			
			for (int d=0; d<4; d++) {
				int nr = c.r+dr[d];
				int nc = c.c+dc[d];
				
				if (isOutofBound(nr, nc) || map[nr][nc] == '1') {
					continue;
				}
				if (moveMap[c.jc][nr][nc] <= c.mc+1) {
					continue;
				}
				
				moveMap[c.jc][nr][nc] = c.mc + 1;
				queue.add(new Coord(nr, nc, moveMap[c.jc][nr][nc], c.jc));

			}
			
			// 말 이동
			if (c.jc == K) {
				continue;
			}
			for (int d=4; d<12; d++) {
				int nr = c.r+dr[d];
				int nc = c.c+dc[d];

				if (isOutofBound(nr, nc) || map[nr][nc] == '1') {
					continue;
				}
				if (moveMap[c.jc+1][nr][nc] <= c.mc+1) {
					continue;
				}
				
				moveMap[c.jc+1][nr][nc] = c.mc + 1;
				queue.add(new Coord(nr, nc, moveMap[c.jc+1][nr][nc], c.jc+1));
				
			}
		}
		
		int minMc = Integer.MAX_VALUE;
		for (int i=0; i<=K; i++) {
			minMc = Math.min(minMc, moveMap[i][H][W]);
		}
		System.out.println(minMc == Integer.MAX_VALUE ? -1 : minMc);
	}
	public static boolean isOutofBound(int r, int c) {
		return r<1 || H<r || c<1 || W<c;
	}

	static class Coord {
		int r, c, mc, jc;
		public Coord (int r, int c, int mc, int jc) {
			this.r = r;
			this.c = c;
			this.mc = mc;
			this.jc = jc;
		}
	}
}
