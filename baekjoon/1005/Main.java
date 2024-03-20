import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int T, N, K, W, maxTime;
    static int[] buildTime, maxBuildTime, indegreeArr;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 건물 수
            K = Integer.parseInt(st.nextToken());   // 건물 규칙 총 수

            st = new StringTokenizer(br.readLine());

            indegreeArr = new int[N + 1];
            maxBuildTime = new int[N + 1];
            buildTime = new int[N + 1];
            adjList = new ArrayList[N + 1];
            for (int i=1; i<=N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
                adjList[i] = new ArrayList<>();
            }

            for (int i=1; i<=K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                indegreeArr[to]++;
            }

            maxTime = 0;
            W = Integer.parseInt(br.readLine());
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                if (indegreeArr[i] == 0) {
                    queue.add(i);
                    maxBuildTime[i] = buildTime[i];
                }
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (curr == W) {
                    maxTime = maxBuildTime[curr];
                    break;
                }

                for (int next : adjList[curr]) {
                    maxBuildTime[next] = Math.max(maxBuildTime[next], buildTime[next] + maxBuildTime[curr]);
                    if (--indegreeArr[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            sb.append(maxTime).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }
}