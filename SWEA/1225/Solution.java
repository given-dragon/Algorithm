import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = 10;

		/**
		 * - queue의 크기는 항상 8로 고정
		 * - 입력을 반복문으로 queue에 넣고 시작
		 * 
		 * 1. 한 숫자가 0이 되지 않으면 계속 사이클을 반복해야함. -> while(true)
		 *    label문법으로 2중 반복문 탈출
		 * 2. 사이클은 for문으로 i 변수를 1~5까지 반복
		 *    2-1. queue에서 앞에 있는 target을 poll()
		 *    2-2. 만약 i보다 크다면 target-i를 queue에 add
		 *    2-3. 값이 0이라면 반복문 모두 종료
		 * 
		 * 3. 결과 출력     
		 **/
		for(int test_case = 1; test_case <= T; test_case++) {
			br.readLine();	// test case 입력 무시
			
			// init queue
			Queue<Integer> queue = new ArrayDeque<>(8);
			for (String numStr : br.readLine().split(" ")) {
				queue.add(Integer.parseInt(numStr));
			}
			
			outer : 
			while (true) {
					for (int i=1; i<=5; i++) {
						int target = queue.poll();
						target = target>i ? target-i : 0;
						queue.add(target);
						
						if (target == 0) {
							break outer;
						}
					}
			}
			
			sb.append("#").append(test_case).append(' ');
			for (int num : queue) {
				sb.append(num).append(' ');
			}
			sb.append('\n');

		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}

