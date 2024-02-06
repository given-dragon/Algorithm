import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t=1; t<=TC; t++) {
			String[] split = br.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);
			
			// 과자 배열 초기화
			split = br.readLine().split(" ");
			int[] snackArr = new int[N];
			for (int i=0; i<N; i++) {
				snackArr[i]= Integer.parseInt(split[i]) ;
			}
			
			Arrays.sort(snackArr);
			
			int lc = 0;	// left
			int rc = N-1;	// right
			
			int maxWeight = -1;	// 들지 못할경우 -1 출력
			while (lc<rc) {
				int weight = snackArr[rc] + snackArr[lc];	// 무게 계산
				
				if (weight<M) {	// 합산 무게가 기준보다 작으면
					maxWeight = Math.max(maxWeight, weight);	// 해당 무게가 최대일 경우
					lc+=1;	// 무게 늘리기
					continue;
				}
				
				if (weight>M) {	// 합산 무게가 크다면 
					rc-=1;	// 무게 줄이기
					continue;
				}
				
				// M == weight
				maxWeight=weight;
				break;
			}
			
			sb.append('#').append(t).append(' ').append(maxWeight).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}

