import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] candyBoxArr;
    static int minDiff;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            candyBoxArr = new int[N];
            for (int i = 0; i < N; i++) {
                candyBoxArr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(candyBoxArr);

            int leftPt = 0;
            int rightPt = K-1;

            minDiff = Integer.MAX_VALUE;
            while (rightPt < N) {
                int diff = candyBoxArr[rightPt++] - candyBoxArr[leftPt++];
                minDiff = Math.min(minDiff, diff);
            }

            sb.append('#').append(tc).append(' ').append(minDiff).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }
}