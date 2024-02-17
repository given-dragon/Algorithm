import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static boolean[] isVisited = new boolean[26];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R, C, maxCount;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken()); 
		
		map = new char[R][C];
		for (int i=0; i<R; i++) {
			String line = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		isVisited[map[0][0]-'A'] = true;
		dfs(0, 0, 1);
		
		bw.write(String.valueOf(maxCount));
		bw.flush();
		
	}
	
	public static void dfs(int r, int c, int count) {
		if (maxCount < count) {
			maxCount = count;
		}
		if (maxCount == 26) {
			return;
		}
		
		for (int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if (nr<0 || R<=nr || nc<0 || C<=nc) {
				continue;
			}
			
			char alpha = map[nr][nc];
			if (isVisited[alpha-'A']) {
				continue;
			}
			
			isVisited[alpha-'A'] = true;
			dfs(nr, nc, count+1);
			isVisited[alpha-'A'] = false;
		}
		
	}
}
