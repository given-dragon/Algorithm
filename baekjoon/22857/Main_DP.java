import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 수열의 길이
        int K = Integer.parseInt(split[1]); // 최대 K번 삭제
        int[] seq = new int[N];

        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(split[i]);
        }

        int[][] dp = new int[K+1][N];

        if (!((seq[0] & 1) == 1)) { // 수열의 시작이 짝수일 경우 초기값 세팅
            for (int j = 0; j <= K; j++) {
                dp[j][0] = 1;
            }
        }
        for (int i = 1; i < N; i++) {   // 수열
            int target = seq[i];
            boolean isEvenNum = !((target & 1) == 1);

            for (int j = 0; j <= K; j++) {   // 삭제 횟수
                if (isEvenNum) {    // 짝수일 경우
                    dp[j][i] = dp[j][i-1] + 1;
                }
                else if (j != 0) {  // 홀수일 경우
                    dp[j][i] = dp[j-1][i-1];
                }
            }
        }
        int result = Arrays.stream(dp[K]).max().getAsInt();
        System.out.println(result);
    }
}