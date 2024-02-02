import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 카드의 개수
		int N = Integer.parseInt(br.readLine());
		
		// 1~N까지 요소를 queue에 추가
		Queue<Integer> queue = new ArrayDeque<>(N+1);
		for (int i=1; i<=N; i++) {
			queue.add(Integer.valueOf(i));
		}
		
		// queue에 요소가 하나 남도록 반복
		while (queue.size() > 1) {
			queue.poll();	// 제일 위의 요소 poll
			queue.add(queue.poll());	// 그 다음 요소는 poll 후 add
		}
		
		// 마지막에 남아있는 요소 출력
		System.out.println(queue.poll());
		br.close();
	}
}

