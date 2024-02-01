import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");
		int S = Integer.parseInt(split[0]);	// 전체 문자열 길이
		int P = Integer.parseInt(split[1]);	// 부분문자열 길이
		
		String tempDNAStr = br.readLine();
		
		split = br.readLine().split(" ");
		int[] threshold = new int[]{
				Integer.parseInt(split[0]), Integer.parseInt(split[1]), 
				Integer.parseInt(split[2]), Integer.parseInt(split[3])};
		
		int[] dnaCount = {0, 0, 0, 0}; // A, C, G, T 염기서열 개수 카운팅
		
		for (int i=0; i<P; i++) {
      int result = isDNA(tempDNAStr.charAt(i));
			dnaCount[result]++;
		}
		
		int passwordCount = isPassword(threshold, dnaCount) ? 1 : 0;
		
		int pt = P-1;
		while (++pt < S) {
			int tail= isDNA(tempDNAStr.charAt(pt));	// 부분 문자열의 끝
			int head = isDNA(tempDNAStr.charAt(pt-P));	// 부분 문자열의 시작
			dnaCount[tail]++;	// 끝의 문자는 계속 갱신되므로 해당하는 염기서열 개수 카운팅
			dnaCount[head]--;	// 시작 문자는 계속 뒤로 밀리므로 없어지는 염개서열 제거
			
			if (isPassword(threshold, dnaCount)) {	// 패스워드 조건에 만족하는지 확인
				passwordCount++;	// 패스워드 개수 증가
			}
		}
		System.out.println(passwordCount);	// 가능한 패스워드 개수 출력
	}
	
	public static int isDNA(char c) {	// 주어진 문자가 DNA 염기서열인지 확인
		switch (c) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		default:
			return -1;
		}
	}
	
	// 패스워드 조건에 만족하는지 확인하는 메서드
	public static boolean isPassword(int[] threshold, int[] dnaCount) {
		// 하나라도 역치 이하라면 false 반환
		if (threshold[0]>dnaCount[0] || threshold[1]>dnaCount[1] || 
				threshold[2]>dnaCount[2] || threshold[3]>dnaCount[3]) {
			return false;
		}
		return true;
	}
}
