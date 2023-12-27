import java.io.*;

class Main {
    static long[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]); // 서쪽 사이트 개수
            int M = Integer.parseInt(split[1]); // 동쪽 사이트 개수

            int n = Math.min(M - N, N);
            arr = new long[M+1][n+1];

            // mCn
            sb.append(dp(M, n)).append('\n');
        }
        System.out.println(sb);
    }

    public static long dp(int m, int n) {
        if (m == n)
            return 1;
        if (n == 0)
            return 1;
        if (arr[m][n] != 0)
            return arr[m][n];

        // mCn = (m-1)C(n-1) + (m-1)Cn
        // mCm == mC0 == 1
        arr[m][n] = dp(m - 1, n - 1) + dp(m - 1, n);
        return arr[m][n];
    }
}