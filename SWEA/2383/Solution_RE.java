import java.io.*;
import java.util.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, minTime;
    static int[][] map;

    static List<Point> manList;
    static List<Stair> stairList;
    static int[] choice;

    static Queue<Integer>[] waitingArr;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            stairList = new ArrayList<>();
            manList = new ArrayList<>();
            waitingArr = new ArrayDeque[2];
            waitingArr[0] = new ArrayDeque<>();
            waitingArr[1] = new ArrayDeque<>();

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 0) {
                        if (map[i][j] == 1) {
                            manList.add(new Point(i, j));
                        }
                        else {
                            stairList.add(new Stair(new Point(i, j), map[i][j]));
                        }
                    }
                }
            }

            choice = new int[manList.size()];
            minTime = Integer.MAX_VALUE;

            recur(0);

            sb.append('#').append(tc).append(' ').append(minTime).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void recur(int idx) {
        if (idx == manList.size()) {
            PriorityQueue<Man> pq = new PriorityQueue<>(Comparator.comparingInt(m->m.time));

            for (int i = 0; i < idx; i++) {
                Point hPoint = manList.get(i);
                Point sPoint = stairList.get(choice[i]).point;

                int time = Math.abs(hPoint.r - sPoint.r) + Math.abs(hPoint.c - sPoint.c);
                pq.offer(new Man(choice[i], time));
            }

            int currentTime = pq.peek().time;
            int outCnt = manList.size();
            while (outCnt > 0) {
                for (Stair stair : stairList) {
                    Queue<Integer> queue = stair.queue;
                    while (!queue.isEmpty() && queue.peek() == currentTime) {
                        queue.poll();
                        outCnt--;
                    }
                }

                for(int i=0; i<2; i++) {
                    Queue<Integer> queue = stairList.get(i).queue;
                    while (queue.size() < 3) {
                        if (waitingArr[i].isEmpty()) {
                            break;
                        }
                        waitingArr[i].poll();
                        queue.add(currentTime + stairList.get(i).len);
                    }
                }

                while (!pq.isEmpty() && pq.peek().time <= currentTime) {
                    Man man = pq.poll();
                    waitingArr[man.stair].add(0);
                }
                currentTime++;
            }

            minTime = Math.min(minTime, currentTime-1);
            return;
        }

        for (int i = 0; i < 2; i++) {
            choice[idx] = i;
            recur(idx + 1);
        }
    }

    static class Stair {
        Queue<Integer> queue;
        Point point;
        int len;
        public Stair(Point point, int len) {
            this.point = point;
            this.len = len;
            queue = new ArrayDeque<>();
        }
    }
    static class Man {
        int stair, time;
        public Man(int stair, int time) {
            this.stair = stair;
            this.time = time;
        }
    }
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}