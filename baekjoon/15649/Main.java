import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static boolean[] isVisited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String[] split = br.readLine().split(" ");
		
		int N = Integer.parseInt(split[0]);	// 1 ~ N까지 자연수
		int M = Integer.parseInt(split[1]);	// M개를 고른 수열
		
		isVisited = new boolean[N+1];
		
		perm(0, N, M, "");
		System.out.println(sb);
	}
	
	public static void perm(int cnt, int N, int M, String seq) {
		if (cnt == M) {	// 길이 끝까지 도달하면 재귀를 종료하고 수열 저장
			sb.append(seq).append('\n');
			return;
		}
		
		for (int i=1; i<=N; i++) {	// N개의 숫자를 반복하며 재귀 실행
			if (isVisited[i]) {	// 해당 경로에서 이미 방문했다면 다른 숫자 선택
				continue;
			}
			
			isVisited[i] = true;	// 숫자를 선택했으므로 방문 체크
			perm(cnt+1, N, M, seq+i+" ");	// 현재까지의 상태를 저장하고 재귀(다음 숫자를 선택)
			isVisited[i] = false;	// 한 경로가 끝났다면 방문 배열을 초기화
		}
	}
}

