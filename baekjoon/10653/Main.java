import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, K;
    CheckPoint[] checkPoints;
    int[][] dp;
    static final int INF = (int) 1e9;

    public void solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) + 1;
        checkPoints = new CheckPoint[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            checkPoints[i] = new CheckPoint(x, y);
        }

        dp = new int[N][K];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if (dp[i][j] == INF) {
                    continue;
                }
                for (int k = 0; k < K; k++) {  // jump
                    int nextCursor = i + k + 1;
                    int jumpSum = j + k;
                    if ((nextCursor >= N) || (jumpSum >= K)) {
                        continue;
                    }
                    CheckPoint currCp = checkPoints[i];
                    CheckPoint nextCp = checkPoints[nextCursor];

                    dp[nextCursor][jumpSum] = Math.min(dp[nextCursor][jumpSum], dp[i][j] + calcDist(currCp, nextCp));
                }
            }
        }

        System.out.println(dp[N-1][K-1]);
    }

    private int calcDist(CheckPoint cp1, CheckPoint cp2) {
        return Math.abs(cp1.x - cp2.x) + Math.abs(cp1.y - cp2.y);
    }
}

class CheckPoint {
    int x, y;
    public CheckPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
