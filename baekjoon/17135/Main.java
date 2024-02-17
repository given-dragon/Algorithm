import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 100;
	static int N, M, D;
	static char[][] map;
	static int[] colSave = new int[3];
	static List<int[]> archerColList = new ArrayList<>();
	static Queue<Coord> killQueue = new ArrayDeque<>();
	static int maxKillCount, maxEnemyRow;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i=0; i<N; i++) {
			String line = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = line.charAt(j<<1);

				if (map[i][j] == '1') {
					maxEnemyRow = Math.min(maxEnemyRow, i);
				}
			}
		}

		setArcherPosComb(0, 0);

		// 각 조합별로 방어 시작
		for (int[] colArr : archerColList) {
			defend(colArr);
		}

		bw.write(String.valueOf(maxKillCount));
		bw.flush();
	}
	
	public static void defend(int[] archerColArr) {
		int killCount = 0;

		for (int row = N; row>=maxEnemyRow; row--) {
			findEnemy(new Coord(row, archerColArr[0]));	// 1번 궁수
			findEnemy(new Coord(row, archerColArr[1]));	// 2번 궁수
			findEnemy(new Coord(row, archerColArr[2]));	// 3번 궁수

			// kill enemy
			for (Coord target : killQueue) {
				if (map[target.r][target.c] == '1')  {
					map[target.r][target.c] = '0';
					killCount++;
				}
			}
		}
		maxKillCount = Math.max(maxKillCount, killCount);

		while (!killQueue.isEmpty()) {
			Coord target = killQueue.poll();
			map[target.r][target.c] = '1';
		}
	}
	
	public static void findEnemy(Coord archer) {
		int fixNum = D-1;	// 궁수의 열을 기준으로 양 옆에 더해줄 변수
		int cnt = D;	// 궁수의 범위를 그리기 위해 사용하는 반복제어 변수

		Coord candiCoord = new Coord(INF, INF);
		int minDist = INF;

		int row = archer.r-1;
		outer:
		while (cnt-- > 0) {
			int startCol = Math.max(0, archer.c - fixNum);
			int endCol = Math.min(archer.c + fixNum, M - 1);

			// 공격 범위의 한 열 체크
			for (int col=startCol; col<=endCol; col++) {
				if (row<0) {
					break outer;
				}
				if (map[row][col] == '0') {	// 빈 공간이라면 pass
					continue;
				}

				int candiDist = Math.abs(archer.r-row) + Math.abs(archer.c-col);
				if (candiDist < minDist) {
					candiCoord.r = row;
					candiCoord.c = col;
					minDist = candiDist;
					if (minDist == 1) {
						break outer;
					}
					continue;
				}
				if (candiDist == minDist) {
					if (col < candiCoord.c) {
						candiCoord.r = row;
						candiCoord.c = col;
					}
				}
			}
			row--;
			fixNum--;
		}

		if (minDist != INF) {
			killQueue.add(candiCoord);
		}
	}
	
	public static void setArcherPosComb(int cnt, int idx) {
		if (cnt == 3) {
			archerColList.add(new int[]{colSave[0], colSave[1], colSave[2]});
			return;
		}
		
		for (int i=idx; i<M; i++) {
			colSave[cnt] = i;
			setArcherPosComb(cnt+1, i+1);
		}
	}
	
	static class Coord {
		int r, c;
		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
