import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] parentArr;
    static Queue<int[]> queue;
    static List<int[]> swanList;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        queue = new ArrayDeque<>();
        swanList = new ArrayList<>();

        // 서로소 집합 배열 초기화
        parentArr = new int[R*C];
        for (int i = 0; i < R * C; i++) {
            parentArr[i] = i;
        }

        isVisited = new boolean[R][C];
        map = new char[R][C];
        for (int i=0; i<R; i++) {

            String line = br.readLine();
            for (int j=0; j<C; j++) {

                map[i][j] = line.charAt(j);
                if (map[i][j] != 'X') { // '.' or 'L'이라면
                    queue.offer(new int[] {i, j});  // 방문 큐에 추가
                    isVisited[i][j] = true; // 방문처리
                }
                if (map[i][j] == 'L') { // 백조의 좌표 저장
                    swanList.add(new int[] {i, j});
                }
            }
        }

        int time = 0;
        while (true) {
            Queue<int[]> nextQueue = bfs(); // 물 탐색 & 녹일 빙하 반환
            if (checkJoin()) {  // 현재 백조가 만날 수 있는지 확인
                System.out.println(time);
                break;
            }

            for (int[] c : nextQueue) { // 빙하 녹이기
                map[c[0]][c[1]] = '.';
            }

            time++; // 시간 1 증가
            queue = nextQueue;  // 빙하가 녹은 자리를 방문 대상으로 설정
        }
    }

    public static void union(int node1, int node2) {
        parentArr[find(node2)] = find(node1);
    }
    public static int find(int node) {
        if (parentArr[node] == node) {
            return node;
        }
        return parentArr[node] = find(parentArr[node]);
    }

    public static boolean checkJoin() {
        int[] swan1 = swanList.get(0);  // 두 백조가 같은 집합이면 -> 만날 수 있음
        int[] swan2 = swanList.get(1);
        return find(swan1[0]*C+swan1[1]) == find(swan2[0]*C+swan2[1]);
    }

    public static Queue<int[]> bfs() {
        Queue<int[]> nextQueue = new ArrayDeque<>();

        while(!queue.isEmpty()) {   // 물을 모두 탐색할 때까지 반복

            int[] target = queue.poll();
            int targetNodeNum = target[0] * C + target[1];
            for (int i=0; i<4; i++) {

                int nr = target[0] + dr[i];
                int nc = target[1] + dc[i];
                if (isOutOfBoundary(nr, nc)) {
                    continue;
                }

                if (map[nr][nc] != 'X') {   // 빙하가 아니라면 union (연결하기 위해 방문을 했어도 다시 합집합 해줌)
                    union(targetNodeNum, nr*C+nc);
                }

                if (isVisited[nr][nc]) {    // 방문을 했다면 continue;
                    continue;
                }
                isVisited[nr][nc] = true;

                if (map[nr][nc] == 'X') {   // 만약 빙하라면 녹을 대상에 추가
                    nextQueue.offer(new int[]{nr, nc});
                }
            }
        }

        return nextQueue;
    }

    public static boolean isOutOfBoundary(int nr, int nc) {
        return nr < 0 || R <= nr || nc < 0 || C <= nc;
    }
}
