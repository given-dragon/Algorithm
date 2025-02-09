import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m, r;
    int[][] adjArr;
    int[] itemCnts;

    public void solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());  // 지역 개수
        m = Integer.parseInt(st.nextToken());  // 수색 범위
        r = Integer.parseInt(st.nextToken());  // 길의 개수

        st = new StringTokenizer(br.readLine());
        itemCnts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            itemCnts[i] = Integer.parseInt(st.nextToken());
        }

        adjArr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adjArr[i], 1501);
            adjArr[i][i] = 0;
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            adjArr[a][b] = l;
            adjArr[b][a] = l;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (adjArr[i][j] > m) {
                    continue;
                }
                sum += itemCnts[j];
            }
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
