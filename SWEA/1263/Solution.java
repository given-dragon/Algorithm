import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static final int MAX = 1_000_001;
	static int[][] adjArr;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			adjArr = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					adjArr[i][j] = Integer.parseInt(st.nextToken());
					if (adjArr[i][j] == 0) {
						adjArr[i][j] = MAX;
					}
				}
				adjArr[i][i] = 0;
			}
			
			for (int k=0; k<N; k++) {
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
					}
				}
 			}
			
			int min = MAX;
			for (int i=0; i<N; i++) {
				for (int j=1; j<N; j++) {
					adjArr[i][j] += adjArr[i][j-1];
				}
				
				min = Math.min(min, adjArr[i][N-1]);
			}
			
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		bw.append(sb);
		bw.flush();
	}
}
