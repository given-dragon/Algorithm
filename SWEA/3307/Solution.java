// BinarySearch Ver
import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[] arr, lisArr;
	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			lisArr = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

				int idx = findIdx(arr[i]);
				lisArr[idx] = arr[i];
				System.out.println(Arrays.toString(lisArr));
			}
			int max = 0;
			for (int num : lisArr) {
				if (num > 0) {
					max++;
					continue;
				}
				break;
			}
			
			sb.append('#').append(t).append(' ').append(max).append('\n');
		}
		
		bw.append(sb);
		bw.flush();
	}
	
	public static int findIdx(int num) {
		int lc=0, rc=N-1;
		
		while (lc <= rc) {
			int mid = ((rc + lc) >> 1);
			
			if (lisArr[mid]==0 || lisArr[mid]>=num) {
				rc = mid-1;
				continue;
			}
			
			//if (lisArr[mid] <= num)
			lc = mid+1;
		}
		
		return lc;
	}
}


// DP Ver
//import java.util.*;
//import java.io.*;
//
//public class Solution {
//	
//	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//	static StringBuilder sb = new StringBuilder();
//	
//	static int N;
//	static int[] arr, lisArr;
//	public static void main(String[] args) throws Exception {
//		
//		int T = Integer.parseInt(br.readLine());
//		
//		for (int t=1; t<=T; t++) {
//			N = Integer.parseInt(br.readLine());
//			arr = new int[N+1];
//			lisArr = new int[N+1];
//			
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			
//			int max = 0;
//			for (int i=1; i<=N; i++) {
//				arr[i] = Integer.parseInt(st.nextToken());
//				
//				for (int j=i-1; j>=0; j--) {
//					if (arr[j] < arr[i]) {
//						lisArr[i] = Math.max(lisArr[i], lisArr[j]+1);
//						max = Math.max(max, lisArr[i]);
//					}
//				}
//			}
//			
//			sb.append('#').append(t).append(' ').append(max).append('\n');
//		}
//		
//		bw.append(sb);
//		bw.flush();
//	}
//}
