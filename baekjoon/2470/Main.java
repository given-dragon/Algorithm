import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        int[] solutions = new int[N];
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(solutions);

        int rc = N-1;   // right cursor
        int lc = 0;  // left cursor

        int candidate = Integer.MAX_VALUE;
        int[] cursor = {lc, rc};    // [0]:lc, [1]:rc
        while (lc<rc) {
            int mix = solutions[lc] + solutions[rc];

            int mixAbs = Math.abs(mix);
            if (mixAbs < candidate) {
                candidate = mixAbs;
                cursor[0] = lc;
                cursor[1] = rc;

                if (candidate == 0) break;
            }

            if (0 < mix) rc--;
            else lc++;
        }

        System.out.printf("%d %d\n", solutions[cursor[0]], solutions[cursor[1]]);
    }
}
