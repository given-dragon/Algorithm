import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static long num = 1_234_567_891L;

    static long[] factorial;
    public static void main(String[] args) throws Exception {

        factorial = new long[1_000_001];
        factorial[0] = 1;
        for (int i=1; i<1_000_001; i++) {
            factorial[i] = (factorial[i-1] * i) % num;
        }

        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long top = factorial[N] % num;
            long bott = factorial[N-R] * factorial[R] % num;

            sb.append('#').append(t).append(' ').append(top * power(bott, num-2) % num).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }

    public static long power(long a, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }

        if ((n & 1) == 1) {
            return (power(a, n-1) * a) % num;
        }

        long i = power(a, n>>1) % num;
        return i * i % num;
    }
}
