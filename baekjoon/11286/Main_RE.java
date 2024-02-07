import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(
				(i1, i2) -> {
					int abs1 = Math.abs(i1); 
					int abs2 = Math.abs(i2);
					if (abs1 == abs2) {
						return Integer.compare(i1, i2);
					}
					
					return Integer.compare(abs1, abs2);
				}
			);
		
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (num == 0) {
				sb.append( pq.isEmpty() ? 0 : pq.poll() )
				.append('\n');
				continue;
			}
			
			pq.add(num);
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}

