import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T;
    static int n, m;

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        int[] prefixA = getPrefixSum(n);

        m = Integer.parseInt(br.readLine());
        int[] prefixB = getPrefixSum(m);

        Map<Long, Integer> subArrB = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                long sum = prefixB[j] - prefixB[i - 1];
                int cnt = subArrB.getOrDefault(sum, 0) + 1;
                subArrB.put(sum, cnt);
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                long sum = prefixA[j] - prefixA[i - 1];
                long target = T - sum;
                ans += subArrB.getOrDefault(target, 0);
            }
        }

        System.out.println(ans);
    }

    public static int[] getPrefixSum(int size) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] prefixArr = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            prefixArr[i] = Integer.parseInt(st.nextToken()) + prefixArr[i - 1];
        }
        return prefixArr;
    }
}
