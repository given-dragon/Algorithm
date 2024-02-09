import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	static int[] foodA;
	static int[][] arr;
	static int N, halfN, minDiff;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 사용할 변수들 초기화
			N = Integer.parseInt(br.readLine());
			halfN = N>>1;
			minDiff = Integer.MAX_VALUE;

			foodA = new int[halfN];
			arr = new int[N][N];
			
			for (int i=0; i<N; i++) {
				String[] split = br.readLine().split(" ");
				for (int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(split[j]);
				}
			}

			// 맛 차이 계산
			dfs(0,0);
			sb.append('#').append(t).append(' ').append(minDiff).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	// NCn/2
	public static void dfs(int cnt, int idx) {
		if (cnt == (halfN)) {
			// foodB 구하기
			int[] foodB = getFoodB();

			// 각 음식의 맛 계산
			int tasteScoreA = calcTaste(foodA);
			int tasteScoreB = calcTaste(foodB);

			// 맛 차이를 구하고, 최소값이라면 갱신
			int diff = Math.abs(tasteScoreA - tasteScoreB);
			minDiff = Math.min(minDiff, diff);
			return;
		}

		// 조합 구하기
		for (int i=idx; i<N; i++) {
			foodA[cnt] = i;	// 선택된 식재로를 foodA에 추가
			dfs(cnt+1, i+1);	// 중복이 발생하지 않도록 i+1를 idx값으로 넣어줌
		}
	}

	// foodA에 없는 식재료를 foodB에 추가
	private static int[] getFoodB() {
		int[] foodB = new int[halfN];
		int c1 = 0;
		int c2 = 0;
		for (int i = 0; i < N; i++) {
			if (c1 >= halfN) {
				c1--;
			}
			if (foodA[c1] == i) {
				c1++;
				continue;
			}
			foodB[c2++] = i;
		}
		return foodB;
	}

	// 식재료의 시너지를 계산하여 맛 계산
	private static int calcTaste(int[] food) {
		int tasteScore = 0;
		for (int i : food) {
			for (int j : food) {
				if (j == i) {
					continue;
				}
				tasteScore += arr[i][j];
			}
		}
		return tasteScore;
	}
}
