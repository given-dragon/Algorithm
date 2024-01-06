import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 동전 개수
        int K = Integer.parseInt(split[1]); // 목표 가치

        int[] coin = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {  // 코인이 하나씩 추가되며
            for (int j = 1; j <= K; j++) {    // 가치 j를 만드는 비용 계산
                if (coin[i] <= j) {
                    dp[j] += dp[j - coin[i]];
                }
            }
        }
        System.out.println(dp[K]);
    }
}

