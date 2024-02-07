import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] arr;
	static int N, M, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		R = Integer.parseInt(split[2])-1;
		
		arr = new int[N][M];
		for (int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(split[j]);
			}
		}
			
		String cmd = br.readLine();
		for (int i=R; i>=0; i--) {
			switch (cmd.charAt(i<<1)) {
			case '1':
				flipHorizontal();
				break;
			case '2':
				flipVertical();
				break;
			case '3':
				turnRight90();
				break;
			case '4':
				turnLeft90();
				break;
			case '5':
				swapGroupForward();
				break;
			case '6':
				swapGroupBackward();
				break;
			}
			N = arr.length;
			M = arr[0].length;
		}
		
		setArr2Str(sb);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 1번 연산
	public static void flipHorizontal() {
		int nN = N-1;
		int[] saveLine;
		for (int i=0; i<(N>>1); i++) {
			saveLine = arr[nN-i];
			arr[nN-i]= arr[i];
			arr[i]= saveLine; 
		}
	}
	
	// 2번 연산
	public static void flipVertical() {
		int nM = M-1;
		int saveVal;
		for (int i=0; i<N; i++) {
			for (int j=0; j<(M>>1); j++) {
				saveVal = arr[i][nM-j];
				arr[i][nM-j] = arr[i][j];
				arr[i][j] = saveVal; 
			}
		}
	}
	
	// 3번 연산
	public static void turnRight90() {
		int nN = N-1;
		int[][] change = new int[M][N];
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				change[i][j]= arr[nN-j][i];
			}
		}
		arr = change;
	}
	
	// 4번 연산
	public static void turnLeft90() {
		int nM = M-1;
		int[][] change = new int[M][N];
		for (int i=nM; i>=0; i--) {
			for (int j=0; j<N; j++) {
				change[nM-i][j]= arr[j][i];
			}
		}
		arr = change;
	}
	
	// 5번 연산
	public static void swapGroupForward() {
		int n = N>>1;
		int m = M>>1;
		
		int[][][] mini = getMiniArr(n, m);

		// 2 -> 3 -> 4 -> 1
		int[] dr = {0, n, n, 0};
		int[] dc = {m, m, 0, 0};
		for (int a=0; a<4; a++) {
			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {
					arr[dr[a]+r][dc[a]+c] = mini[a][r][c];
				}
			}
		}
	}
	
	// 6번 연산
	public static void swapGroupBackward() {
		int n = N>>1;
		int m = M>>1;
		
		int[][][] mini = getMiniArr(n, m);

		// 4 -> 1 -> 2 -> 3
		int[] dr = {n, 0, 0, n};
		int[] dc = {0, 0, m, m};
		for (int a=0; a<4; a++) {
			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {
					arr[dr[a]+r][dc[a]+c] = mini[a][r][c];
				}
			}
		}
	}

	private static int[][][] getMiniArr(int n, int m) {
		int[] dr = {0, 0, n, n};
		int[] dc = {0, m, m, 0};
		int[][][] mini = new int[4][n][m];
		for (int a=0; a<4; a++) {
			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {
					mini[a][r][c] = arr[r+dr[a]][c+dc[a]];
				}
			}
		}
		return mini;
	}
	
	public static void setArr2Str(StringBuilder sb) {
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[0].length; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
	}
}
