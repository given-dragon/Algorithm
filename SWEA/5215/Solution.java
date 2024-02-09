import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static int N, L, maxScore;
	static Ingredient[] ingredients;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			String[] split = br.readLine().split(" ");
			
			N = Integer.parseInt(split[0]);	// 재료의 수
			L = Integer.parseInt(split[1]);	// 제한 칼로리
			ingredients = new Ingredient[N];

			// 재료배열 초기화
			for (int i=0; i<N; i++) {
				split = br.readLine().split(" ");
				ingredients[i]= new Ingredient(
						Integer.parseInt(split[0]), 
						Integer.parseInt(split[1])); 
			}

			maxScore = 0;
			find(0, 0, 0);
			
			sb.append('#').append(t).append(' ').append(maxScore).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void find(int next, int score, int kcal) {
		if (next == N) {	// 최대 개수의 재료를 선택하면 maxScore 갱신
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		for (int i=next; i<N; i++) {
			// 현 재료 + 다음 재료의 점수와 칼로리를 계산
			int scoreSum = score+ingredients[i].score;
			int kcalSum = kcal+ingredients[i].kcal;

			// 만약 칼로리가 기준 이상이라면, 현 재료만 고른 점수를 maxScore와 비교하여 반영
			if (kcalSum > L) {
				maxScore = Math.max(maxScore, score);
				continue;
			}

			// 현 재료 + 다음 재료를 선택하고 계속 진행
			find(i+1, scoreSum, kcalSum);
		}
	}
	static class Ingredient {	// 재료 객체
		int score, kcal;
		public Ingredient(int score, int kcal) {
			this.score = score;
			this.kcal = kcal;
		}
	}
}
