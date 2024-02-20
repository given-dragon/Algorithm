import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final char VISITED = '3', MELT = '2',  CHEESE = '1';
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static int row, col;
	static List<int[]> meltList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new char[row][col];
		for (int i=0; i<row; i++) {
			String line = br.readLine();
			for (int j=0; j<col; j++) {
				map[i][j] = line.charAt(j<<1);
			}
		}
		
		meltList.add(new int[] {0, 0});
		map[0][0] = VISITED;
		
		int time = 0;
		int lastCheeseCount = 0;
		while (true) {
			int cheeseCount = bfs(meltList);
			if (cheeseCount == 0) {
				break;
			}

			lastCheeseCount = cheeseCount;
			time++;
		}
		
		System.out.println(time + "\n" + lastCheeseCount);
	}
	
	public static int bfs(List<int[]> meltList) {
		Queue<int[]> queue = new ArrayDeque<>(meltList);
		meltList.clear();
		
		while (!queue.isEmpty()) {
			int[] target = queue.poll();
			
			for (int i=0; i<4; i++) {
				int nr = target[0] + dr[i];
				int nc = target[1] + dc[i];
				
				if (nr < 0 || row <= nr || nc < 0 || col <= nc) {
					continue;
				}
				if (map[nr][nc] == VISITED || map[nr][nc] == MELT) {
					continue;
				}
				
				if (map[nr][nc] == CHEESE) {
					map[nr][nc] = MELT;
					meltList.add(new int[] {nr, nc});
					continue;
				}
				
				queue.add(new int[] {nr, nc});
				map[nr][nc] = VISITED;
			}
		}
		
		for (int[] coord : meltList) {
			map[coord[0]][coord[1]] = '0';
		}
		return meltList.size();
	}
}
