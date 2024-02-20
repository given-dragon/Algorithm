import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static char[][] map;
	static boolean[][] visitedInfo;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i=0; i<N; i++) {
			String line = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = line.charAt(j);
			}	
		}

		visitedInfo = new boolean[N][N];

		int normal = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (visitedInfo[i][j]) {
					continue;
				}
				
				bfs(i, j, map[i][j], true);
				normal++;
			}	
		}

		int blind = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visitedInfo[i][j]) {
					continue;
				}
				
				bfs(i, j, map[i][j], false);
				blind++;
			}	
		}
		
		System.out.println(normal + " " + blind);
	}

	public static void bfs(int r, int c, char color, boolean isNormal) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		visitedInfo[r][c] = isNormal;
		
		while (!queue.isEmpty()) {
			int[] target = queue.poll();
			
			for (int i=0; i<4; i++) {
				int nr = target[0] + dr[i];
				int nc = target[1] + dc[i];
				
				if (nr < 0 || N <= nr || nc < 0 || N <= nc) {
					continue;
				}
				
				if (isVisited(isNormal, visitedInfo[nr][nc]) || !isSameColor(isNormal, color, map[nr][nc])) {
					continue;
				}
				
				queue.add(new int[] {nr, nc});
				visitedInfo[nr][nc] = isNormal;
			}
		}
	}
	
	public static boolean isVisited(boolean isNormal, boolean visited) {
		return isNormal == visited;
	}

	public static boolean isSameColor(boolean isNormal, char startColor, char curColor) {
		if (isNormal) {
			return startColor == curColor;
		}
		if (startColor == 'B') {
			return startColor == curColor;
		}
		return curColor == 'R' || curColor == 'G';
	}
}
