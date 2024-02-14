package 이준용;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<int[]> teamCombList = new ArrayList<>(15);
	static int[][] compareResult;
	static int[][] gameResult = new int[6][3];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		comb(0, 0, new int[2]);

		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int scoreSum = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					gameResult[i][j] = st.nextToken().charAt(0) - '0';
					scoreSum += gameResult[i][j];
				}
			}

			boolean isValid = false;
			if (scoreSum == 30) {
				isValid = rec2(0);
//				compareResult = new int[6][3];
//				isValid = rec(0);
			}
			sb.append(isValid ? '1' : '0').append(' ');
		}
		System.out.println(sb);
	}

	public static boolean rec(int cnt) {
		if (cnt == 15) {
			// 모든 팀이 경기를 마치고 조합한 경기 결과와 입력의 일치 여부 비교
			for (int i = 0; i < 6; i++) {
				// 결과의 팀마다 승리, 무승부, 패배 결과를 비교 => 하나라도 다르면 false 리턴
				if (gameResult[i][0] != compareResult[i][0] ||
					gameResult[i][1] != compareResult[i][1] ||
					gameResult[i][2] != compareResult[i][2])
				{
					return false;
				}
			}
			return true;
		}

		// 현재 경기의 home팀과, away팀
		int home = teamCombList.get(cnt)[0];
		int away = teamCombList.get(cnt)[1];

		// 다음 조건이 만족할 때 성립할 경우, 홈 VS 어웨이 경기에서 홈의 승리 가능성 존재
		// 입력된 게임 결과에 home의 승리 횟수가 1이상, away의 패배 횟수가 1이상
		if (gameResult[home][0] > 0 && gameResult[away][2] > 0) {
			compareResult[home][0]++;
			compareResult[away][2]++;
			if (rec(cnt + 1)) {
				return true;
			}
			compareResult[home][0]--;
			compareResult[away][2]--;
		}

		// 다음 조건이 만족할 때 성립할 경우, 홈 VS 어웨이 경기에서 홈의 패배 가능성 존재
		// 입력된 게임 결과에 home의 패배 횟수가 1이상, away의 승리 횟수가 1이상
		if (gameResult[home][2] > 0 && gameResult[away][0] > 0) {
			compareResult[home][2]++;
			compareResult[away][0]++;
			if (rec(cnt + 1)) {
				return true;
			}
			compareResult[home][2]--;
			compareResult[away][0]--;
		}

		// 다음 조건이 만족할 때 성립할 경우, 홈 VS 어웨이 경기에서 무승부 가능성 존재
		// 입력된 게임 결과에 home, away의 무승부 횟수가 1이상
		if (gameResult[home][1] > 0 && gameResult[away][1] > 0) {
			compareResult[home][1]++;
			compareResult[away][1]++;
			if (rec(cnt + 1)) {
				return true;
			}
			compareResult[home][1]--;
			compareResult[away][1]--;
		}

		return false;
	}

	// rec와 유사하지만 반복문 비교를 없앤 메서드
	public static boolean rec2(int cnt) {
		if (cnt == 15) {
			/*
			rec와 차이점은 탐색 시에 입력 배열의 값을 1씩 제외하고, 원하는 결과가 아닐경우 복구해줌.

			만약 입력 배열인 gameResult가 올바르지 않은 경기 결과라면 해당 메서드의 조건문 때문에 cnt가 15에 도달할 수 없음.
			=> 완탐시 배열을 따로 만들어 반복문으로 비교해줄 필요가 없음
			 */
			return true;
		}

		int home = teamCombList.get(cnt)[0];
		int away = teamCombList.get(cnt)[1];

		// 홈 승리
		if (gameResult[home][0] > 0 && gameResult[away][2] > 0) {
			gameResult[home][0]--;
			gameResult[away][2]--;
			if (rec2(cnt + 1)) {
				return true;
			}
			gameResult[home][0]--;
			gameResult[away][2]--;
		}

		// 홈 패배
		if (gameResult[home][2] > 0 && gameResult[away][0] > 0) {
			gameResult[home][2]--;
			gameResult[away][0]--;
			if (rec2(cnt + 1)) {
				return true;
			}
			gameResult[home][2]++;
			gameResult[away][0]++;
		}

		// 무승부
		if (gameResult[home][1] > 0 && gameResult[away][1] > 0) {
			gameResult[home][1]--;
			gameResult[away][1]--;
			if (rec2(cnt + 1)) {
				return true;
			}
			gameResult[home][1]++;
			gameResult[away][1]++;
		}

		return false;
	}

	// 경기를 진행하는 팀의 조합을 만드는 메서드
	public static void comb(int cnt, int idx, int[] teamArr){
		if (cnt == 2) {
			teamCombList.add(new int[]{teamArr[0], teamArr[1]});
			return;
		}
		for (int i = idx; i < 6; i++) {
			teamArr[cnt] = i;
			comb(cnt+1, i+1, teamArr);
		}
	}
}