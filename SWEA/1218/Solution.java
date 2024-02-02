import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T= 10;

		/**
		 * - ArrayDeque를 stack으로 사용 (addLast, removeLast만 사용)
		 * - stack의 크기는 주어진 문자열 크기의 절반으로 지정
		 * 
		 * 1. 문자열의 문자가 여는 괄호라면 스택에 삽입
		 * 2. 만약 닫는 괄호라면 현재 스택의 last요소와 짝이 맞는지 확인
		 * 3. 짝이라면 1부터 계속 진행, 짝이 아니라면 유효하지 않은 문자열이기 때문에 break
		 * 4. 스택이 비어있다면 유효한 문자열 -> 1, 비어있지 않다면 유효하지 않은 문자열 -> 0 출력
		 **/
		for(int test_case = 1; test_case <= T; test_case++){
			int len = Integer.parseInt(br.readLine());
			String str = br.readLine();
			ArrayDeque<Character> stack = new ArrayDeque<>(len>>1);
			
			for (int i=0; i<len; i++) {
				char c = str.charAt(i);
				
				// 여는 괄호라면 스택에 추가
				if (c == '[' || c == '{' || c == '(' || c == '<') {
					stack.addLast(c);	
					continue;
				}
				
				// 닫는 괄호와 짝이 맞는지 체크
				if (check(stack, c)) {
					continue;
				}
				
				// 짝이 맞지 않다면 반복문 종료
				break;
			}
			
			// 결과 출력
			int result = stack.isEmpty() ? 1 : 0;
			sb.append('#').append(test_case).append(' ').append(result).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static boolean check(ArrayDeque<Character> stack, char c) {
		if (stack.isEmpty()) {	// 스택이 비어있는 상태에서 닫는 괄효라면 유효하지 않음
			stack.add('&'); // 예외처리위해 상관없는 문자 삽입
			return false;
		}
		
		char compare = stack.peekLast();
		if (compare == '[' && c == ']') {
			stack.removeLast();
			return true;
		}
		if (compare == '{' && c == '}') {
			stack.removeLast();
			return true;
		}
		if (compare == '(' && c == ')') {
			stack.removeLast();
			return true;
		}
		if (compare == '<' && c == '>') {
			stack.removeLast();
			return true;
		}
		
		return false;
	}
}

