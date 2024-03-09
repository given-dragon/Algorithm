import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] score;
    static long B;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        score = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(score);

        long lc = 0, rc = Integer.MAX_VALUE, ans = 0;
        while (lc+1<rc) {
            long mc = (rc+lc)>>1;
            long cost = 0;
            for (int i=0; cost<=B && i<N; i++) {
                if (score[i] >= mc) {
                    continue;
                }
                cost += (long) Math.pow(mc-score[i], 2);
            }

            if (cost <= B) {
                ans = Math.max(ans, mc);
                lc = mc;
                continue;
            }
            rc = mc;
        }

        System.out.println(ans);
    }
}

