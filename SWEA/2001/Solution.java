import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case=1; test_case<=T; test_case++) {
			
			String[] split = br.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);
			
			/*
			아이디어
			1. 2차원 누적합 구하기
			2. M*M 만큼의 구간합 도출
			3. 최대 파리 수 갱신
			 */
			int[][] board = new int[N+1][N+1];
			for (int i=1; i<=N; i++) {
				split = br.readLine().split(" ");
				for (int j=1; j<=N; j++) {
					board[i][j]= Integer.parseInt(split[j-1]) + board[i-1][j]+ board[i][j-1] - board[i-1][j-1];   
				}
			}
			
			int maxCount = 0;

			// 파리채의 크기는 M으로 시작 좌표를 M, M으로 설졍해야 (0, 0) ~ (M, M)의 부분합을 구할 수 있다.
			for (int i=M; i<=N; i++) {
				for (int j=M; j<=N; j++) {
					int count = board[i][j]- board[i][j-M] - board[i-M][j] + board[i-M][j-M];  
					maxCount = Math.max(maxCount, count);
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(maxCount).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
