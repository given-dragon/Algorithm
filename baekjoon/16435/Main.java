import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int L = Integer.parseInt(split[1]);

		split = br.readLine().split(" ");
		int[] h = new int[N];
		for (int i=0; i<N; i++) {
			h[i] = Integer.parseInt(split[i]);
		}
		
		Arrays.sort(h);
		for (int i=0; i<N; i++) {
			if (L >= h[i]) {
				L++;
			}
		}
		
		System.out.println(L);
	}
}
