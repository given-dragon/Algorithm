import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, C;
    static int[] housePosArr;
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        housePosArr = new int[N];
        for (int i = 0; i < N; i++) {
            housePosArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(housePosArr);
        System.out.println(findMaxDist());
    }

    public static int findMaxDist() {
        int lc = 1;
        int rc = housePosArr[housePosArr.length-1] - housePosArr[0];

        int maxDist = lc;

        while (lc < rc) {
            int mid = (lc + rc + 1) >> 1;

            if (check(mid)) {
                lc = mid;
                maxDist = Math.max(maxDist, mid);
                continue;
            }
            rc = mid - 1;
        }

        return maxDist;
    }

    private static boolean check(int mid) {
        int start = 0;
        int cnt = 0;
        for (int i = start + 1; i < housePosArr.length; i++) {
            if (housePosArr[i] - housePosArr[start] >= mid) {
                cnt++;
                start = i;
            }
        }
        return cnt >= C-1;
    }
}
