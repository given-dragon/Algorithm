import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int S = Integer.parseInt(split[1]);

        split = br.readLine().split(" ");
        int[] seq = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(split[i - 1]);
            seq[i] = seq[i - 1] + num;
        }

        // 연속된 수의 idx가 1,2,3 까지라면 rc->3, lc->0
        int rc = 1; // right cursor;
        int lc = 0; // left cursor;

        int minLen = Integer.MAX_VALUE;
        while (rc <= N) {
            int sum = seq[rc] - seq[lc];

            if (sum < S) {
                rc++;
            }
            else {
                minLen = Math.min(minLen, rc - lc);
                lc++;
            }
        }

        if (minLen == Integer.MAX_VALUE)
            minLen = 0;
        System.out.println(minLen);
    }
}
