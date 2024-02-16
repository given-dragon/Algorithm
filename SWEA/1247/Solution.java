import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
    static int[][] nodePosList;
    static boolean[] isVisited;
    static int minDist, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine()) + 2;
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            // 고객 좌표
            // [0]:회사, [1]:집, [2~]:고객
            nodePosList = new int[N][2];
            for (int i=0; i<N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                nodePosList[i] = new int[] {x, y};
            }
             
            isVisited = new boolean[N];
            isVisited[0] = true;
            isVisited[1] = true;
             
            minDist = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            sb.append('#').append(t).append(' ').append(minDist).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }
     
    public static void dfs(int cnt, int curr, int dist) {
         
        // 현재 거리가 최소 거리보다 크다면 더이상 탐색 의미 없음
        if (minDist < dist) {
            return;
        }
        // 고객 집을 다 방문
        if (cnt == N-2) {
            // 기존 최소 거리, 거리 + 마지막 고객 ~ 집 거리 중 최솟값 선택
            minDist = Math.min(minDist, dist + calcDist(curr, 1));
            return;
        }

        // 노드를 돌며 dfs
        for (int v=2; v<N; v++) {
            if (isVisited[v]) {
                continue;
            }
             
            isVisited[v] = true;
            dfs(cnt+1, v, dist+calcDist(curr, v));
            isVisited[v] = false;
        }
    }

    // 거리 계산
    public static int calcDist(int v1, int v2) {
        return Math.abs(nodePosList[v1][0] - nodePosList[v2][0]) + Math.abs(nodePosList[v1][1] - nodePosList[v2][1]);
    }
}
