import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, M; // N: 김밥의 개수, K: 꼬다리 길이, M: 조각 최소 개수
    static List<Integer> kimbapList;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        kimbapList = new ArrayList<>(N);

        int k2 = K << 1;
        for (int i = 0; i < N; i++) {
            int kimbap = Integer.parseInt(br.readLine());

            if (kimbap <= K) {
                continue;
            }
            int cuttingLen = (kimbap < k2) ? K : k2;
            kimbap -= cuttingLen;

            if (kimbap <= 0) {
                continue;
            }
            kimbapList.add(kimbap);
        }

        if (kimbapList.isEmpty()) {
            System.out.println(-1);
            return;
        }

        int maxLen = K;
        for (int kimbap : kimbapList) {
            maxLen = Math.max(maxLen, kimbap);
        }
        int lp = 0;
        int rp = maxLen;

        while (lp < rp) {
            int mp = (lp + rp + 1) >> 1;

            long m = cuttingKimbap(mp);
            if (m < M) {
                rp = mp - 1;
            } else {
                lp = mp;
            }
        }

        if (lp == 0) {
            lp = -1;
        }
        System.out.println(lp);
    }

    private static long cuttingKimbap(int p) {
        long cnt = 0;
        for (int kimbap : kimbapList) {
            cnt += (kimbap / p);
        }
        return cnt;
    }
}
