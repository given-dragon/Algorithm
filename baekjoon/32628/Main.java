import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static long[] prefixA, prefixB;

    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 배낭 안 물건의 개수
        K = Integer.parseInt(st.nextToken());  // 제거 가능한 횟수

        prefixA = new long[N + 1];
        initPrefixArr(prefixA);
        prefixB = new long[N + 1];
        initPrefixArr(prefixB);

        long result = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            int rmCntA = Math.min(N, i);

            int rmCntB = K - rmCntA;
            rmCntB = Math.min(N, rmCntB);

            int ptrA = N - rmCntA;
            int ptrB = N - rmCntB;

            result = Math.min(result, Math.max(prefixA[ptrA], prefixB[ptrB]));
        }

        System.out.println(result);
    }

    private static void initPrefixArr(long[] arr) throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }
    }
}
