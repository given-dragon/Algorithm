import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static final int LEFT=6, RIGHT=2;
	static int K;
	static Cog[] gearArr = new Cog[4];
	static int[][] adjArr = {{-1, 1}, {0, 2}, {1,3}, {2, -1}};
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			K = Integer.parseInt(br.readLine());
			
			for (int i=0; i<4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				char[] topArr = new char[8];
				for (int j=0; j<8; j++) {
					topArr[j] = st.nextToken().charAt(0);
				}
				gearArr[i] = new Cog(topArr);
			}
			
			while (K-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = st.nextToken().charAt(0)-'0';
				int dir = Integer.parseInt(st.nextToken());
				
				visited = new boolean[4];
				find(idx-1, dir);
			}
			
			int score = 0;
			for (int i=0; i<4; i++) {
				if (gearArr[i].edges[0] == '1') {
					score += (int) Math.pow(2, i);
				}
			}
			
			sb.append('#').append(tc).append(' ').append(score).append('\n');
		}
		bw.append(sb);
		bw.flush();
	}

	public static void find(int idx, int dir) {
		if (visited[idx]) {
			return;
		}
		visited[idx] = true;
		
		Cog root = gearArr[idx];
		for (int i = 0; i < 2; i++) {
			int cIdx = adjArr[idx][i];
			if (cIdx >= 0) {
				Cog left = i==0 ? gearArr[cIdx] : root;
				Cog right = i==0 ? root : gearArr[cIdx];
				if (left.edges[RIGHT] != right.edges[LEFT]) {
					find(cIdx, -1*dir);
				}
			}
		}

        if (dir > 0) {
            turnRight(root.edges);
        }
		else {
            turnLeft(root.edges);
        }
	}

	public static void turnRight(char[] topArr) {
		char save = topArr[7];
		for (int i=7; i>0; i--) {
			topArr[i] = topArr[i-1]; 
		}
		topArr[0] = save;
	}
	public static void turnLeft(char[] topArr) {
		char save = topArr[0];
		for (int i=0; i<7; i++) {
			topArr[i] = topArr[i+1]; 
		}
		topArr[7] = save;
	}
	static class Cog {
		char[] edges;
		public Cog(char[] edges) {
			this.edges = edges;
		}
	}
}
