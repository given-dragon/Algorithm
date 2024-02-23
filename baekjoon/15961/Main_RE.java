import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    public static void main(String[] args) throws Exception {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());	// 접시 수
    	int d = Integer.parseInt(st.nextToken());	// 초밥 가짓수
    	int k = Integer.parseInt(st.nextToken());	// 연속 가능한 접시 수
    	int c = Integer.parseInt(st.nextToken());	// 쿠폰 번호
    	
    	int[] sushiBag = new int[d+1];
    	sushiBag[c] = 1;
    	
    	int[] sushiLine = new int[N];
    	for (int i=0; i<N; i++) {
    		sushiLine[i] = Integer.parseInt(br.readLine());
    	}
    	
    	int fc = 0;	// front
    	int rc = 0;	// rear
    	
    	int sushiCount = 1;
    	for(int i=0; i<k; i++) {
    		if (sushiBag[sushiLine[fc]] == 0) {
    			sushiCount++;
    		}
    		sushiBag[sushiLine[fc]]++;	// 하나 먹기
    		fc++;	// 포인터 증가
    	}
    	
    	int maxSushiCount = 0;
    	int last = fc-1;
    	for (; fc!=last; fc = (fc+1)%N) {
    		int inSushi = sushiLine[fc];
    		if (sushiBag[inSushi]++ == 0) {
    			sushiCount++;
    		}
    		
    		int outSushi = sushiLine[rc];
    		if (--sushiBag[outSushi] == 0) {
    			sushiCount--;
    		}
    		rc = (rc+1)%N;
    		
    		maxSushiCount = Math.max(maxSushiCount, sushiCount);
    	}
    	
    	System.out.println(maxSushiCount);
    }
}

