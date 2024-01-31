import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] numbers;	// 출력할 수열 저장할 배열
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String NM = br.readLine();	// N, M을 포함한 문자열 입력받기

		// N, M 저장
		N = Character.getNumericValue(NM.charAt(0));
		M = Character.getNumericValue(NM.charAt(2));
		
		numbers = new int[N];
		
		comb(0, 1, sb);	// 조합 재귀함수 시작
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void comb(int cnt, int start, StringBuilder sb) {
		if (cnt == M) {	// cnt가 M이 되면 sb에 수열 저장 후 종료
			for (int i=0; i<M; i++) {
				sb.append(numbers[i]).append(' ');
			}
			sb.append('\n');	// 수열 하나를 출력하면 개행
			return;
		}
		
		for (int i=start; i<=N; i++) {	// start ~ N까지 반복
			numbers[cnt] = i;	// cnt(depth)를 수열의 인덱스로 사용하여 숫자 저장
			comb(cnt+1, i+1, sb);	// 재귀적으로 들어감
		}
	}
}

