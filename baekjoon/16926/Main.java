import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N, M, R;
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		R= Integer.parseInt(split[2]);
		arr = new int[N][M];
		for (int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for (int j=0; j<M; j++) {
				arr[i][j]= Integer.parseInt(split[j]); 
			}
		}

		// 배열 회전 횟수
		for (int i=0; i<R; i++) {
			rotateArr(0, 0, N, M);
		}
		
		// 출력
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void rotateArr(int r, int c, int rowCount, int colCount) {
		// depth를 하나씩 들어가며 회전
		for (int rotateDepth = Math.min(N, M)/2; rotateDepth > 0; rotateDepth--) {
			int beforeNum = arr[r][c];
			int currentNum;
			
			int alpha = 1;
			// 한 껍데기씩 회전 -> 한 회전에 row, col 이동이 세트로 2번
			for (int t=2; t>0; t--) {
				
				// row
				for (int i=1; i<rowCount; i++) {	// N-1번 반복
					r += alpha;
					currentNum = arr[r][c];
					arr[r][c]= beforeNum;
					beforeNum = currentNum;
				}
				
				// col
				for (int i=1; i<colCount; i++) {	// N-1번 반복
					c += alpha;
					currentNum = arr[r][c];
					arr[r][c]= beforeNum;
					beforeNum = currentNum;
				}
				
				alpha *= -1;
			}
			r+=1;
			c+=1;
			rowCount-=2;
			colCount-=2;
		}
	}
}

