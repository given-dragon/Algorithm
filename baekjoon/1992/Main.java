import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static char[][] image;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		image = new char[N][N];
		for (int i=0; i<N; i++) {
			String line = br.readLine();
			for (int j=0; j<N; j++) {
				image[i][j] = line.charAt(j);
			}	
		}
		
		rec(N, 0, 0);

		System.out.println(sb.toString());

	}

	public static void rec(int N, int r, int c) {
		
		int result = cmpCheck(N, r, c);
		if (result > -1) {
			sb.append((char)result);
			return;
		}

		sb.append('(');
		int halfN = N>>1;
		rec(halfN, r, c);
		rec(halfN, r, c+halfN);
		rec(halfN, r+halfN, c);
		rec(halfN, r+halfN, c+halfN);
		sb.append(')');
	}
	
	public static int cmpCheck(int N, int r, int c) {
		int target = image[r][c];
		for(int i=r; i<r+N; i++) {
			for(int j=c; j<c+N; j++) {
				if (target==image[i][j]) {
					continue;
				}
				return -1;
			}	
		}
		return image[r][c];
	}
}
