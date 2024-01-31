import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 입력받기

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			int num = 1;	// 배열에 저장될 변수
			int[][] board = new int[N][N];
			for (int i=0; i<N; i++) {	// 가장 윗줄 먼저 초기화 (무조건 N만큼 반복)
				board[0][i] = num++;	// 저장 후 1씩 증가
			}
			

			int count = (N-1)<<1;	// 반복문 횟수 결정( (n-1)*2 )
			int alpha = 1;	// 배열의 증&감을 결정할 변수
			
			// 배열의 위치를 가리킬 row, col
			int row = 0;
			int col = N-1;	// 0행의 열은 모두 채워졌으므로 마지막부터 시작
			for (int i=1; i<=count; i++) {
				// 행 & 열을 채우는 반복이 매번 N-i만큼 일어남
				
				for (int j=0; j<N-i; j++) {	// 행을 채우는 반복문
					row += alpha;	// 행을 증감시킴
					board[row][col] = num++;
				}
				
				alpha *= -1;	// 행&열의 증감이 반복되도록 -1을 곱해 부호 변환
				
				for (int j=0; j<N-i; j++) {	// 열을 채우는 반복문
					col += alpha;
					board[row][col] = num++;
				}
			}
			
			
			sb.append('#').append(test_case).append('\n');	// 테스트 케이스 문자열 추가
			for (int i=0; i<N; i++) {	// 배열을 반복하며 StringBuilder에 저장
				for (int j=0; j<N; j++) {
					sb.append(board[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		
		bw.write(sb.toString());	// 결과 출력
		bw.flush();	// 버퍼 비움
		br.close();	// reader close
		bw.close();	// writer close
	}
}

