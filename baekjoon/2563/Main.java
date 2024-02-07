import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		
		for (int i=0; i<N; i++) {
			String[] split = br.readLine().split(" ");
			int x = Integer.parseInt(split[0])-1;
			int y = Integer.parseInt(split[1])-1;
			
			map[y][x] += 1;
			map[y+10][x] += -1;
			map[y][x+10] += -1;
			map[y+10][x+10] += 1;
		}
		
		for (int i=0; i<=100; i++) {
			for (int j=1; j<=100; j++) {
				map[i][j] += map[i][j-1]; 
			}
		}
		
		for (int i=1; i<=100; i++) {
			for (int j=0; j<=100; j++) {
				map[i][j] += map[i-1][j]; 
			}
		}
		
		int totalArea = 0;
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (map[i][j] > 0) {
					totalArea+=1;
				}
			}
		}
		
		System.out.println(totalArea);
	}
}

