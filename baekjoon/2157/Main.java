import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, K;
    static List<Airplane>[] adjList;
    static int[][] maxScore;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 총 도시 수
        M = Integer.parseInt(st.nextToken());   // 제한 도시 수
        K = Integer.parseInt(st.nextToken());   // 항공로 개수

        maxScore = new int[M + 1][N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a > b) {
                continue;
            }
            adjList[a].add(new Airplane(b, c, 0));
        }

        Queue<Airplane> queue = new ArrayDeque<>();
        queue.add(new Airplane(1, 0, 1));

        while (!queue.isEmpty()) {
            Airplane target = queue.poll();

            if (target.cnt >= M) {
                continue;
            }
            for (Airplane child : adjList[target.dest]) {

                int nScore = maxScore[target.cnt][target.dest] + child.score;
                if (nScore <= maxScore[target.cnt+1][child.dest]) {
                    continue;
                }

                maxScore[target.cnt+1][child.dest] = nScore;
                queue.add(new Airplane(child.dest, child.score, target.cnt + 1));
            }
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            answer = Math.max(answer, maxScore[i][N]);
        }

        bw.append(String.valueOf(answer));
        bw.flush();
    }

    static class Airplane {
        int dest, score, cnt;
        public Airplane(int dest, int score, int cnt) {
            this.dest = dest;
            this.score = score;
            this.cnt = cnt;
        }
    }
}
