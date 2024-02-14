import java.io.*;
public class Main {
	static int count, result;
	static int N, tr, tc, powN;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		tr = Integer.parseInt(split[1]);
		tc = Integer.parseInt(split[2]);
		
		powN = (int) Math.pow(2, N);
		
		rec(powN, 0, 0);
		System.out.println(count);
	}
	
	public static void rec(int N, int r, int c) {
		if (N == 1 || (r==tr && c==tc)) {
			return;
		}
		
		int halfN = N>>1;
		
		int rowThres =halfN + r;
		int colThres =halfN + c;
		
		if (tr < rowThres) {	// 1, 2 사분면
			
			// 1 사분면
			if (tc < colThres) {
				rec(halfN, r, c);
				return;
			}
			
			// 2 사분면
			count += halfN*halfN;
			if (tc >= colThres) {
				rec(halfN, r, c+halfN);
				return;
			}
		}
		else {	// 3, 4 사분면
			
			// 3 사분면
			count += halfN*halfN*3;
			if (tc >= colThres) {
				rec(halfN, r+halfN, c+halfN);
				return;
			}
			
			// 4 사분면
			count -= halfN*halfN;
			if (tc < colThres) {
				rec(halfN, r+halfN, c);
				return;
			}
		}
	}
}
