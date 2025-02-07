import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N, M;
    final int MAX_DIST = 1001;

    public void solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] adjArr = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(adjArr[i], MAX_DIST);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjArr[a][b] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (adjArr[i][j] < MAX_DIST | adjArr[j][i] < MAX_DIST) {
                    cnt++;
                }
            }
            if (cnt == (N-1)) {
                result++;
            }
        }

        System.out.println(result);
    }
}
