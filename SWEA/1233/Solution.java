import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		for (int test_case=1; test_case<=10; test_case++) {
			
			int N = Integer.parseInt(br.readLine());	// 노드 개수
			boolean result = true;	// 계산 가능여부를 저장하는 변수
			
			for (int j = 1; j <= N; j++) { 
				// 이미 계산이 안된다고 판정되었다면 한줄 입력만 받고 무시
				if (!result) {
					br.readLine();
					continue;
				}
				
				// 입력을 split
				String[] split = br.readLine().split(" ");
				char val = split[1].charAt(0);	// 기호 or 숫자의 첫번째만 보면 됨
				
				// 자식이 있는 노드일 경우 => 숫자 노드이면 안됨
				if (split.length > 2) {
					if ('0' <= val && val <= '9') {	// 숫자이면 계산 불가
						result = false;
					}
					continue;
				}
				
				// 자식이 없는 노드일 경우 => 기호이면 안됨
				if (val == '+' || val == '-' || val == '*' || val == '/') {
					result = false;
				}
			}
			
			// 출력응ㄹ sb에 저장
			sb.append('#').append(test_case).append(' ').append(result ? 1 : 0).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}

