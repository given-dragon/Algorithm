import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] split = br.readLine().split(" ");
		
		int[] buildingArr = new int[N+1];	
		for (int i=1; i<=N; i++) {
			buildingArr[i]= Integer.parseInt(split[i-1]);
		}
		buildingArr[0] = Integer.MAX_VALUE;
		
		ArrayDeque<Building> stack = new ArrayDeque<>();
		int[] result = new int[N+1];
		
		for (int i=N; i>=0; i--) {
			while (!stack.isEmpty() && stack.peekLast().height < buildingArr[i]) {
				Building polled = stack.pollLast();
				result[polled.idx] = i;
			}
			stack.addLast(new Building(buildingArr[i], i));
		}
		
		for (int i=1; i<=N; i++) {
			sb.append(result[i]).append(' ');
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	static class Building {
		int height, idx;
		public Building(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}
	}
}
