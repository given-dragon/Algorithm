import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static char[][] map;
	static int H, W;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			Tank tank = new Tank(0, 0);

			// 맵 입력받기
			map = new char[H][W];
			for (int i=0; i<H; i++) {
				String line = br.readLine();
				for (int j=0; j<W; j++) {
					map[i][j] = line.charAt(j);

					// 만약 전차라면 위치 저장
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '>' || map[i][j] == '<') {
						tank.r = i;
						tank.c = j;
					}
				}
			}
			
			int cmdCount = Integer.parseInt(br.readLine());
			String cmdLine = br.readLine();
			for (int i=0; i<cmdCount; i++) {
				char cmd = cmdLine.charAt(i);

				if (cmd == 'S') {
					tank.shoot();
					continue;
				}
				tank.move(cmd);	// 전차 이동
			}

			sb.append('#').append(t).append(' ');
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

	static class Tank {
		int r, c;
		public Tank(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void shoot() {
			int alpha = (map[r][c] == 'v' || map[r][c] == '>') ? 1 : -1;
			
			if ((map[r][c] == '^' || map[r][c] == 'v')) {
				int nr = r+alpha;	// 포탄의 row
				while(!isOutOfBoundary(nr, c)) {
					if (map[nr][c] == '*') {
						map[nr][c] = '.';
						break;
					}
					if (map[nr][c] == '#') {
						break;	// break
					}
					nr+=alpha;
				}
				return;
			}

			int nc = c+alpha;
			while(!isOutOfBoundary(r, nc)) {
				if (map[r][nc] == '*') {
					map[r][nc] = '.';
					break;
				}
				if (map[r][nc] == '#') {
					break;
				}
				nc+=alpha;
			}

		}
		
		public void move(char cmd) {
			int nr = r;
			int nc = c;

			switch (cmd) {
				case 'U':
					map[r][c] = '^';
					nr--;
					break;
				case 'D':
					map[r][c] = 'v';
					nr++;
					break;
				case 'L':
					map[r][c] = '<';
					nc--;
					break;
				case 'R':
					map[r][c] = '>';
					nc++;
					break;
			}
			
			if(isMovable(nr, nc)) {	// 움직일 수 있는 경우라면 좌표 수정
				map[nr][nc] = map[r][c];
				map[r][c] = '.';
				r = nr;
				c = nc;
			}
		}
		
		public boolean isMovable(int nr, int nc) {
			if (isOutOfBoundary(nr, nc)) {
				return false;
			}
			return map[nr][nc] == '.';
		}
		
		public boolean isOutOfBoundary(int nr, int nc) {
            return nr < 0 || H <= nr || nc < 0 || W <= nc;
        }
	}
}

