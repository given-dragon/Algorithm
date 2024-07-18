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

        ArrayList<Wire> lis = new ArrayList<>();
        int[] dp = new int[N];

        for (int i=0; i<N; i++) {
            Wire wire = wireArr[i];

            int lc = 0;
            int rc = lis.size();
            while (lc < rc) {
                int mid = (lc + rc) >> 1;
                if (lis.get(mid).numB > wire.numB) {
                    rc = mid;
                    continue;
                }
                lc = mid + 1;
            }

            if (lis.size() <= lc) {
                lis.add(wire);
            } else {
                lis.set(lc, wire);
            }
            dp[i] = lc + 1;
        }

        List<Integer> result = new ArrayList<>();
        int max = lis.size();
        for (int i = N-1; i >= 0; i--) {
            if (dp[i] == max) {
                max--;
                continue;
            }
            result.add(wireArr[i].numA);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N-lis.size()).append('\n');
        for (int i = result.size() - 1; i >= 0; i--) {
            sb.append(result.get(i)).append('\n');
        }
        System.out.println(sb);
    }

    static class Wire {
        int numA, numB;

        public Wire(int numA, int numB) {
            this.numA = numA;
            this.numB = numB;
        }
    }
}
