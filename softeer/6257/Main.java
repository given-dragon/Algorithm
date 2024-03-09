import java.io.*;
import java.util.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 1번 차례 올 버스 선택
        // 3번 차례 버스 탐색 (1번버스 번호 > 3번버스 번호)
        int[][] cnt = new int[N+1][N+1];  // [fst][sec] == fst->sec->trd cnt
        for (int fst=0; fst<N-2; fst++) {
            for (int trd=N-1; trd>=0; trd--) {
                cnt[arr[fst]][trd] = cnt[arr[fst]][trd+1] + ((arr[fst]>arr[trd]) ? 1 : 0);
            }
        }

        long ans = 0;
        for (int fst=0; fst<N; fst++) {
            for (int sec=fst+1; sec<N; sec++) {
                if (arr[fst] < arr[sec]) {
                    ans+=cnt[arr[fst]][sec];    
                }
            }
        }

        System.out.println(ans);
    }
}

