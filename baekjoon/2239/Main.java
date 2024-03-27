import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int[][] map = new int[9][9];
	static List<Point> zeroArr = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		
		for (int i=0; i<9; i++) {
			String line = br.readLine();
			for (int j=0; j<9; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (map[i][j] == 0) {
					zeroArr.add(new Point(i, j));
				}
			}
		}
		
		findNum(0);
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		bw.append(sb);
		bw.flush();
	}
	
	
	public static boolean findNum(int idx) {
		if (idx == zeroArr.size()) {
			return true;
		}
		
		Point p = zeroArr.get(idx);
		boolean[] nCheck = new boolean[10];
		
		// row check
		for (int i=0; i<9; i++) {
			nCheck[map[p.r][i]] = true;
		}
		
		// col check
		for (int i=0; i<9; i++) {
			nCheck[map[i][p.c]] = true;
		}
		
		// mini check
		int rs = p.r/3*3;
		int cs = p.c/3*3;
		for (int i=rs; i<rs+3; i++ ) {
			for (int j=cs; j<cs+3; j++) {
				nCheck[map[i][j]] = true;
			}
		}
		
		
		for (int k=1; k<=9; k++) {
			if (!nCheck[k]) {
				map[p.r][p.c] = k;
				if (findNum(idx+1)) {
					return true;
				}
				map[p.r][p.c] = 0;
			}
		}
		return false;
	}
	
	static class Point {
		int r, c;
		public Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
