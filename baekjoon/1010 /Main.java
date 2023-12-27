import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]); // 서쪽 사이트 개수
            int M = Integer.parseInt(split[1]); // 동쪽 사이트 개수

            int n = Math.min(M - N, N);

            // mCn
            long ans = 1;
            for (int j = M-n+1; j <= M; j++) {
                ans *= j;
            }

            int bottom = 1;
            for (int r = 2; r <=n; r++) {
                ans /= r;
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}