import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, B;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 횡당보도의 수(번호)
        K = Integer.parseInt(st.nextToken());  // 연속해야하는 횡단보도의 수
        B = Integer.parseInt(st.nextToken());  // 고장난 신호등의 개수

        int[] prefixSum = new int[N + 1];
        int b = B;
        while (b-- > 0) {
            int num = Integer.parseInt(br.readLine());
            prefixSum[num] = 1;
        }

        for (int i = 1; i <= N; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        int lc = 0;
        int rc = K;
        int min = prefixSum[rc] - prefixSum[lc];

        while (rc < N) {
            lc++;
            rc++;
            min = Math.min(min, prefixSum[rc] - prefixSum[lc]);
        }

        System.out.println(min);
    }
}