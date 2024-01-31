import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// N, M 입력받기
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]); 
		int M = Integer.parseInt(split[1]);
		
		split = br.readLine().split(" ");
		int[] numArr = new int[N+1];	// 1~N 인덱스를 사용하기 위해 N+1 크기로 선언
		for (int i=1; i<=N; i++) {
			numArr[i] = numArr[i-1] + Integer.parseInt(split[i-1]);	// 누적합을 구함
		}
		
		for (int t=1; t<=M; t++) {
			
			// i, j입력받음
			split = br.readLine().split(" ");
			int i = Integer.parseInt(split[0]); 
			int j = Integer.parseInt(split[1]);
			
			int sum = numArr[j] - numArr[i-1];	// 구간합 구하기
			sb.append(sum).append('\n');	// 개행
		}
		bw.write(sb.toString());	// 정답 출력
		bw.flush();	// 버퍼 비움
		br.close();	// reader close
		bw.close();	// writer close
	}
}

