import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int D, P;
    Pipe[] pipes;

    public void solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());  // 수도관의 길이
        P = Integer.parseInt(st.nextToken());  // 파이프의 개수

        pipes = new Pipe[P];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());  // 길이
            int C = Integer.parseInt(st.nextToken());  // 용량
            pipes[i] = new Pipe(L, C);
        }

        long[] dist = new long[D + 1];

        for (Pipe p : pipes) {
            for (int d = D; d > 0; d--) {
                int leftLen = d - p.L;
                if (leftLen < 0) {
                    continue;
                }

                if (leftLen == 0) {
                    dist[d] = Math.max(dist[d], p.C);
                    continue;
                }
                dist[d] = Math.max(dist[d], Math.min(dist[leftLen], p.C));
            }
        }

        System.out.println(dist[dist.length-1]);
    }
}

class Pipe {
    int L, C;
    public Pipe(int L, int C) {
        this.L = L;
        this.C = C;
    }
}
