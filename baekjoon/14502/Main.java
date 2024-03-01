import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, M, emptyCnt, maxSafeCnt;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] map;
	
	static List<Coord> vList = new ArrayList<>();
	static List<Coord> emptyCoordList = new ArrayList<>(64);
	
	public static void main(String[] args) throws Exception {
		String NM = br.readLine();
		N = NM.charAt(0) - '0';
		M = NM.charAt(2) - '0';
		
		map = new char[N][M];
		for (int i=0; i<N; i++) {

			String line = br.readLine();
			for (int j=0; j<M; j++) {

				map[i][j] = line.charAt(j<<1);
				if (map[i][j] == '0') {
					emptyCoordList.add(new Coord(i, j));
					continue;
				}
				if (map[i][j] == '2') {
					vList.add(new Coord(i, j));
				}
			}
		}
		emptyCnt = emptyCoordList.size()-3;
		simulation();
		
		System.out.println(maxSafeCnt);
	}

	public static void simulation() {
		Coord[] wallSave = new Coord[3];

		for (int fst=0; fst<emptyCoordList.size(); fst++) {
			for (int snd=fst+1; snd<emptyCoordList.size(); snd++) {
				for (int trd=snd+1; trd<emptyCoordList.size(); trd++) {

					wallSave[0] = emptyCoordList.get(fst);
					wallSave[1] = emptyCoordList.get(snd);
					wallSave[2] = emptyCoordList.get(trd);
					
					for (Coord w : wallSave) {
						map[w.r][w.c] = '1';
					}

					maxSafeCnt = Math.max(maxSafeCnt, emptyCnt-bfs());
					
					for (Coord w : wallSave) {
						map[w.r][w.c] = '0';
					}
				}
			}
		}
	}
	
	public static int bfs() {
		Queue<Coord> queue = new ArrayDeque<>(vList);
		boolean[][] visited = new boolean[N][M];
		for (Coord v : vList) {
			visited[v.r][v.c] = true;
		}
		
		int notSafeCnt = 0;
		while (!queue.isEmpty()) {
			Coord v = queue.poll();
			
			for (int i=0; i<4; i++) {
				int nr = v.r + dr[i];
				int nc = v.c + dc[i];
				
				if (nr<0 || N<=nr || nc<0 || M<=nc || visited[nr][nc]) {
					continue;
				}
				
				visited[nr][nc] = true;
				if (map[nr][nc] == '0') {
					notSafeCnt++;
					queue.add(new Coord(nr, nc));
				}
			}
		}
		
		return notSafeCnt;
	}
	
	static class Coord {
		int r, c;
		public Coord (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
