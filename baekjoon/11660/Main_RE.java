import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] split = br.readLine().split(" "); 
		int N = Integer.parseInt(split[0]);	// 표의 크기
		int M = Integer.parseInt(split[1]);	// 합을 구하는 횟수
		
		int[][] sumBoard = new int[N+1][N+1];	// 1,1 ~ r,c 까지의 합을 저장할 배열
		
		// init board
		for (int i=1; i<=N; i++) {
			split = br.readLine().split(" ");
			for (int j=1; j<=N; j++) {
				
				// sumBoard[i][j]는 1,1부터 i,j까지의 합
				sumBoard[i][j] = Integer.parseInt(split[j-1]) + sumBoard[i-1][j] + sumBoard[i][j-1] - sumBoard[i-1][j-1];
			}
		}
		
		// 구간합 구하기 시작
		for (int t=0; t<M; t++) {
			
			// 각 좌표 생성
			split = br.readLine().split(" ");
			Point p1 = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			Point p2 = new Point(Integer.parseInt(split[2]), Integer.parseInt(split[3]));
			
			// 구간합 구하기
			int sum = sumBoard[p2.row][p2.col] - sumBoard[p1.row-1][p2.col] - sumBoard[p2.row][p1.col-1] + sumBoard[p1.row-1][p1.col-1]; 
			
			sb.append(sum).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}

