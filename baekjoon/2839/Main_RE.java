import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		while(N > 0) {
			if (N % 5 == 0) {
				N-=5;
				cnt+=1;
				continue;
			}
			
			N-=3;
			cnt+=1;
		}
		
		System.out.println(N<0 ? -1 : cnt);
	}
}

