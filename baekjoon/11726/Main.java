import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+2];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
            dp[i] %= 10007; // 나중에 모듈러를 하면 값이 너무 커서 매번 모듈러연산
        }

        System.out.println(dp[n]);
    }
}
// 1x2 타일은 어차피 2개를 사용해서 2x2만 사용 가능
// 즉, 해당 문제는 (1, 2)를 사용하여 n으로 만들 수 있는 개수 문제로 변환가능