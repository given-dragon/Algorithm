import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int L, C;
	static char[] alpha, pwSave;
	public static void main(String[] args) throws Exception {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());	// 암호 길이
		C = Integer.parseInt(st.nextToken());	// 알파벳 개수
		alpha = new char[C];
		pwSave = new char[L];
		
		String line = br.readLine();
		for (int i=0; i<C; i++) {
			alpha[i] = line.charAt(i<<1);
		}
		
		Arrays.sort(alpha);
		makePassword(0, 0, 0, 0);
		
		bw.append(sb);
		bw.flush();
	}
	
	public static void makePassword(int len, int idx, int consCount, int vowelCount) {
		if (len == L) {
			if (consCount < 2 || vowelCount < 1) {
				return;
			}
				
			for (char c : pwSave) {
				sb.append(c);
			}
			sb.append('\n');
			return;
		}
		
		for (int i=idx; i<C; i++) {
			pwSave[len] = alpha[i];
			if (alpha[i]=='a'||alpha[i]=='e'||alpha[i]=='i'||alpha[i]=='o'||alpha[i]=='u') {
				makePassword(len+1, i+1, consCount, vowelCount+1);
				continue;
			}
			
			makePassword(len+1, i+1, consCount+1, vowelCount);
		}
	}
}
