import java.util.*;
import java.io.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K, maxSize; // N&M:초기 세포 크기, K 주어진 시간

    static int[][] map;   // 맵의 최대 크기는 Max(N, M)=>maxSize, [maxSize+K][maxSize+K]

    static Queue<Cell> unActiveQueue, activeQueue;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            init();

            int currTime = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int ni = i+K;
                    int nj = j+K;
                    map[ni][nj] = Integer.parseInt(st.nextToken());
                    if (map[ni][nj] > 0) {
                        unActiveQueue.add(getCell(ni, nj, currTime));
                    }
                }
            }

            currTime++;
            for (; currTime <= K; currTime++) {
                // 비활성화 검사 -> 활성화
                int qSize = unActiveQueue.size();
                for (int i = 0; i < qSize; i++) {
                    Cell target = unActiveQueue.poll();
                    if (target.startTime == currTime) {
                        activeQueue.add(target);
                    } else {
                        unActiveQueue.add(target);
                    }
                }

                // 활성화 검사
                Map<Integer, Integer> candiCellMap = new HashMap<>();
                qSize = activeQueue.size();
                for (int i = 0; i < qSize; i++) {
                    Cell target = activeQueue.poll();

                    // 활성화 검사 -> 번식 후보 선정
                    if (target.startTime + 1 == currTime) {
                        for (int d = 0; d < 4; d++) {
                            int nr = target.r+dr[d];
                            int nc = target.c+dc[d];
                            if (map[nr][nc] > 0) {
                                continue;
                            }

                            int key = maxSize * nr + nc;
                            int nt = target.endTime - target.startTime;
                            if (candiCellMap.containsKey(key)) {
                                nt = Math.max(nt, candiCellMap.get(key));
                            }
                            candiCellMap.put(key, nt);
                        }
                    }

                    // 활성화 검사 -> 사망자 버림
                    if (target.endTime != currTime) {
                        activeQueue.add(target);
                    }
                }

                // 활성화 검사 -> 번식
                for (Map.Entry<Integer, Integer> entry : candiCellMap.entrySet()) {
                    int r = entry.getKey()/maxSize;
                    int c = entry.getKey()-r*maxSize;
                    map[r][c] = entry.getValue();
                    unActiveQueue.add(getCell(r, c, currTime));
                }
            }
            int answer = activeQueue.size() + unActiveQueue.size();
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }

    private static Cell getCell(int r, int c, int currTime) {
        return new Cell(r, c,
                currTime + map[r][c],
                currTime + map[r][c] + map[r][c]
        );
    }

    public static void init() throws IOException {
        unActiveQueue = new ArrayDeque<>();
        activeQueue = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maxSize = Math.max(N, M) + K*2;
        map = new int[maxSize][maxSize];
    }

    static class Cell {
        int r, c, startTime, endTime;
        public Cell(int r, int c, int startTime, int endTime) {
            this.r = r;
            this.c = c;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}