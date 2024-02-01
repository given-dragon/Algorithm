import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	/*
	 * sb : StringBuilder
	 * candiArr : 가장 왼쪽 숫자를 제외한 자리에 들어갈 수 있는 후보 숫자
	 * N : 숫자의 자릿수
	 */
	static StringBuilder sb;
	static final int[] candiArr = {1, 3, 7, 9};
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		
		// 가장 왼쪽에 들어갈 수 있는 소수는 2, 3, 5, 7
		// 해당 숫자를 시작으로 dfs 시작
		for (int start : new int[] {2, 3, 5, 7}) {
			findPrime(1, start);
		}
		
		// 결과 출력
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// dfs 메서드
	public static void findPrime (int cnt, int num) {
		if (cnt == N) {	// 자릿수가 N이라면 출력
			sb.append(num).append('\n');
			return;
		}
		
		// 해당 자리수가 소수라면 숫자를 더해가며 dfs
		for (int nextNum : candiArr) {
			int candidate = num * 10 + nextNum;
			
			if (isPrime(candidate)) {
				findPrime(cnt+1, candidate);
			}
		}
	}
	
	// 소수 판별 메서드
	public static boolean isPrime(int num) {
		if ((num&1) == 0) { // 짝수이면 false
			return false;
		}
		
		// num의 제곱근까지 나눌 수 있는 수가 있는지 확인
		for (int i=3; i<=Math.sqrt(num); i+=2) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}

}
