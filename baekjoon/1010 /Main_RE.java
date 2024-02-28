import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N, M;
	static int[][] B;
	public static void main(String[] args) throws Exception{
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
//			B = new int[M+1][N+1];
//			iter();
			
			B = new int[M+1][N+1];
			recur(M, N);
			sb.append(B[M][N]).append('\n');
		}
		
		bw.append(sb);
		bw.flush();
	}

	private static void iter() {
		for (int i=0; i<=M; i++) {
			for (int j=0, end=Math.min(i, N); j<=end; ++j) {
				if (i==j || j==0) {
					B[i][j] = 1;
					continue;
				}
				
				B[i][j] = B[i-1][j-1] + B[i-1][j];
			}
		}
		
		sb.append(B[M][N]).append('\n');
	}
	
	public static int recur(int n, int k) {
		if (B[n][k]!=0) {
			return B[n][k];
		}
		if (n==k || k==0) {
			return B[n][k] = 1;
		}
		
		return B[n][k] =  recur(n-1, k-1) + recur(n-1, k);
	}
}
