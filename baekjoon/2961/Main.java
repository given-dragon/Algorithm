import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 재료의 개수
		Food[] ingredArr = new Food[N];	// N만큼 재료 배열 선언
		
		// 재료 배열 초기화(신맛, 쓴맛 저장)
		for (int i=0; i<N; i++) {
			String[] split = br.readLine().split(" ");
			ingredArr[i] = new Food(
					Integer.parseInt(split[0]), 
					Integer.parseInt(split[1]));
		}
		
		int minResult = Integer.MAX_VALUE;	// 최소값을 저장하기 위해 max_value 저장
		int caseCount = (int) Math.pow(2,  N);	// 경우의 수는 2^n개
		
		// 비트 마스크 -> 0001부터 비교를 시작하면 공집합인 경우 없음
		for (int mixCase = 1; mixCase < caseCount; mixCase++) {
			Food food = new Food(1, 0);	// 신맛은 값이 곱해지므로 1로 초기화, 쓴맛은 0으로 초기화
			
			for (int i=0; i<N; i++) {	// 각 비트를 순회
				int foodIdx = mixCase & (1<<i);	// 1을 오른쪽으로 i번 비트이동, &연산으로 들어갈 재료 파악
				
				if (foodIdx == 0) {	// 들어갈 재료가 아니면 continue
					continue;
				}
				
				// 들어갈 재료라면 값 계산, 저장
				food.sourFalv *= ingredArr[i].sourFalv;
				food.bitterFlav += ingredArr[i].bitterFlav;
			}
			
			// 신맛, 쓴맛 차이가 음수로 나올 수 있으므로 절대값으로 계산
			int result = Math.abs(food.sourFalv - food.bitterFlav);
			minResult = Math.min(minResult, result);	// 최소값 저장
		}
		
		System.out.println(minResult);
	}

	static class Food {
		int sourFalv;
		int bitterFlav;
		public Food(int sourFlav, int bitterFlav) {
			this.sourFalv = sourFlav;
			this.bitterFlav = bitterFlav;
		}
	}
}
