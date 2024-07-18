import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Wire[] wireArr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        wireArr = new Wire[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int numA = Integer.parseInt(st.nextToken());
            int numB = Integer.parseInt(st.nextToken());
            wireArr[i] = new Wire(numA, numB);
        }

        Arrays.sort(wireArr, Comparator.comparingInt(wire -> wire.numA));

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {

            int target = wireArr[i].numB;
            for (int j = 0; j < i; j++) {
                if (wireArr[j].numB < target) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(N-max);
    }

    static class Wire {
        int numA, numB;

        public Wire(int numA, int numB) {
            this.numA = numA;
            this.numB = numB;
        }
    }
}
