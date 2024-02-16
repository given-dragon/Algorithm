import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0,  0, 1, 0,-1};
	static BC[] bcArr;
	static User userA, userB;
	static int chargeScore;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());	// 총 이동 시간
			int A = Integer.parseInt(st.nextToken());	// BC의 개수

			String moveA = "0 "+br.readLine();	// user-A의 이동경로 (0번째 시간은 움직이지 않음)
			String moveB = "0 "+br.readLine();	// user-B의 이동경로 (0번째 시간은 움직이지 않음)

			// 충전기의 정보 입력받음
			bcArr = new BC[A];
			for (int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcArr[i] = new BC(row, col, c, p);
			}
			// 충전기를 power의 내림차순으로 정렬
			Arrays.sort(bcArr, (BC bc1, BC bc2) -> -1*Integer.compare(bc1.p, bc2.p));

			chargeScore = 0;	// 두 유저의 충전 합계를 저장할 변수
			userA = new User(1, 1);
			userB = new User(10, 10);

			for (int time = 0; time <= M; time++) {
				// 유저 이동
				userA.move(moveA.charAt(time<<1) - '0');
				userB.move(moveB.charAt(time<<1) - '0');

				// 유저가 있는 속한 충전기 초기화
				userA.bcNumList.clear();
				userB.bcNumList.clear();

				// 충전기와 거리를 비교하며 속해있다면 리스트에 추가
				// 내림차순으로 정렬되어 있어 idx가 적을수록 최선
				for (int num = 0; num < A; num++) {
					if (isOnBC(bcArr[num], userA)) {
						userA.bcNumList.add(num);
					}
					if (isOnBC(bcArr[num], userB)) {
						userB.bcNumList.add(num);
					}
				}

				charge();

			}

			sb.append('#').append(t).append(' ').append(chargeScore).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void charge() {
		// 둘 중 하나라도 충전기에 없는 경우
		if (userA.bcNumList.isEmpty() || userB.bcNumList.isEmpty()) {
			chargeScore += userA.bcNumList.isEmpty() ? 0 : bcArr[userA.bcNumList.get(0)].p;
			chargeScore += userB.bcNumList.isEmpty() ? 0 : bcArr[userB.bcNumList.get(0)].p;
			return;
		}

		// 이후부터 나오는 조건은 0번째 충전기를 무조건 선택
		chargeScore += bcArr[userA.bcNumList.get(0)].p;

		// 다른 충전기에 있는 경우
		// top이 다르다면, 다른 충전기 분배 가능
		if (!userA.bcNumList.get(0).equals(userB.bcNumList.get(0))) {
			chargeScore += bcArr[userB.bcNumList.get(0)].p;
			return;
		}

		// 같은 충전기에 있는 경우
		// 둘 다 유일할 경우
		if (userA.bcNumList.size() == 1 && userB.bcNumList.size() == 1) {
			return;
		}

		// 만약 둘 중 하나라도 다른 충전기가 있다면
		if (userA.bcNumList.size() == 1) {
			chargeScore += bcArr[userB.bcNumList.get(1)].p;
			return;
		}
		if (userB.bcNumList.size() == 1) {
			chargeScore += bcArr[userA.bcNumList.get(1)].p;
			return;
		}

		// 두 지역 공유 & 다른 지역 포함 => 상위 지역 다음게 큰걸 선택
		chargeScore += Math.max(bcArr[userA.bcNumList.get(1)].p, bcArr[userB.bcNumList.get(1)].p);
	}

	static boolean isOnBC(BC bc, User user) {
		return bc.c >= Math.abs(bc.row-user.row) + Math.abs(bc.col-user.col);
	}
	
	static class User {
		int row, col;
		List<Integer> bcNumList;
		public User (int row, int col) {
			this.row = row;
			this.col = col;
			bcNumList = new ArrayList<>(8);
		}

		public void move(int cmd) {
			row += dr[cmd];
			col += dc[cmd];
		}
	}
	
	static class BC {
		int row, col, c, p;
		public BC(int row, int col, int c, int p) {
			this.row = row;
			this.col = col;
			this.c = c;
			this.p = p;
		}
	}
}

