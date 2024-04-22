import java.util.*;
import java.io.*;


/**
 * 통나무의 중심(r, c)과 방향(0:가로, 1:세로)만 저장
 * bfs를 통해 최단거리 구하기.
 * visited 배열은 통나무의 방향을 기준으로 2차원 배열 사용 [방향][row][col]
 *
 * [로직]
 * [초기 설정]
 * 큐에 통나무 좌표 추가
 * 방향을 기준으로 visited 배열에 방문 처리
 *
 * [메인]
 * 0. while (not queue.isEmpty())
 * 1. target = 큐.poll()
 * 2. 도착지 검사 => 타겟, 도착지의 중심좌표 일치여부 && 방향 일치여부
 *    최소횟수 저장 후 반복문 종료
 *
 * 3. 통나무 이동 시작
 * 3-1. 상, 하, 좌, 우 이동
 *      dr, dc를 통해 중앙 좌표 이동 => nr, nc
 *      nr, nc에 통나무 방향에 따라 양 옆 좌표 구하기
 *      세 좌표가 모두 이동 가능하면 visited 처리 & 큐에 추가
 * 3-2. 회전 이동
 *      중앙 좌표는 고정
 *      중앙 좌표를 기준으로 3*3배열 검사 => 이동 가능하면 방향만 회전하여 visited 처리 & 큐에 추가
 *      nDir = (방향 + 1) % 2
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] sideR = {{0, 0}, {-1, 1}};
    static int[][] sideC = {{-1, 1}, {0, 0}};

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[2][N][N];

        List<int[]> start = new ArrayList<>();
        List<int[]> end = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'B') {
                    start.add(new int[]{i, j});
                }
                else if (map[i][j] == 'E') {
                    end.add(new int[]{i, j});
                }
            }
        }

        Log startLog = new Log(start.get(1)[0], start.get(1)[1], start.get(0)[0] == start.get(1)[0] ? 0 : 1, 0);
        Log endLog = new Log(end.get(1)[0], end.get(1)[1], end.get(0)[0] == end.get(1)[0] ? 0 : 1, 0);

        Queue<Log> queue = new ArrayDeque<>();
        queue.add(startLog);
        visited[startLog.dir][startLog.r][startLog.c] = true;

        int answer = 0;
        outer:
        while (!queue.isEmpty()) {
            Log target = queue.poll();

            if (isEnd(target, endLog)) {
                answer = target.t;
                break;
            }

            // U, D, L, R
            for (int d = 0; d < 4; d++) {
                int nr = target.r + dr[d];
                int nc = target.c + dc[d];

                // center check
                if (isOutOfBound(nr, nc) || visited[target.dir][nr][nc]) {
                    continue;
                }
                //side check
                if (isOutOfBound(nr+sideR[target.dir][0], nc+sideC[target.dir][0]) ||
                        isOutOfBound(nr+sideR[target.dir][1], nc+sideC[target.dir][1])) {
                    continue;
                }

                queue.add(new Log(nr, nc, target.dir, target.t + 1));
                visited[target.dir][nr][nc] = true;
            }

            // T
            int nDir = (target.dir+1) % 2;
            if (visited[nDir][target.r][target.c]) {
                continue;
            }
            int si = target.r-1;
            int sj = target.c-1;
            for (int i = si; i < si+3; i++) {
                for (int j = sj; j < sj+3; j++) {
                    if (isOutOfBound(i, j)) {
                        continue outer;
                    }
                }
            }
            queue.add(new Log(target.r, target.c, nDir, target.t + 1));
            visited[nDir][target.r][target.c] = true;
        }

        bw.append(String.valueOf(answer));
        bw.flush();
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || N<=r || c<0 || N<= c || map[r][c]=='1';
    }

    public static boolean isEnd(Log target, Log endLog) {
        return target.r == endLog.r && target.c == endLog.c && target.dir == endLog.dir;
    }

    static class Log {
        int r, c, dir, t;
        public Log(int r, int c, int dir, int t) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.t = t;
        }
    }
}
