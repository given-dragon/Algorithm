import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Solution {

	static ArrayList<Integer> gy;
	static ArrayList<Integer> iy;
	static int winCount;
	static boolean[] isVisited = new boolean[9];
	static final int DECK_COUNT = 9;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			winCount = 0;
			String[] split = br.readLine().split(" ");
			boolean[] check = new boolean[19];
			gy = new ArrayList<>();
			for (int i=0; i<DECK_COUNT; i++) {
				gy.add(Integer.parseInt(split[i]));
				check[gy.get(i)] = true;
			}
			
			iy = new ArrayList<>();
			for (int i=1; i<=18; i++) {
				if (!check[i]) {
					iy.add(i);
				}
			}
			
			permutation(0, 0, 0);
						
			sb.append('#').append(t).append(' ').append(winCount).append(' ').append(362880-winCount).append('\n');
			
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void permutation(int cnt, int gyScore, int iyScore) {
		if (cnt == DECK_COUNT) {
			if (gyScore > iyScore) {
				winCount++;	
			}
			return;
		}
		
		for (int i=0; i<DECK_COUNT; i++) {
			if (isVisited[i]) {
				continue;
			}
			
			int gPick = gy.get(cnt);
			int iPick = iy.get(i);
			
			isVisited[i] = true;
			if (gPick > iPick) {
				permutation(cnt+1, gyScore+gPick+iPick, iyScore);
			}
			else {
				permutation(cnt+1, gyScore, iyScore+gPick+iPick);
			}
			isVisited[i] = false;
		}
	}
}

