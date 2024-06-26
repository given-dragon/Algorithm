import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m, maxSum;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static List<Coord>[] routeList;
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][n];

        map = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Coord[] start = new Coord[m];
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            start[i] = new Coord(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);
        }

        int[][][] perm = {
                {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}},
                {{0, 1}, {1, 0}},
                {{0}}
        };

        int answer = 0;
        routeList = new List[m];
        for (int[] order: perm[3-m]) {
            int totalSum = 0;
            for (int i=0; i<m; i++) {
                routeList[i] = new ArrayList<>();
            }

            for (int i : order) {
                maxSum = 0;
                List<Coord> route = new ArrayList<>();
                visited[start[i].r][start[i].c] = true;
                route.add(new Coord(start[i].r, start[i].c, map[start[i].r][start[i].c]));
                recur(i, 0, map[start[i].r][start[i].c], route);
                totalSum += maxSum;
            }

            answer = Math.max(answer, totalSum);
        }
        System.out.println(answer);
    }

    public static void recur(int no, int cnt, int sum, List<Coord> coordList) {
        if (cnt == 3)  {
            if (maxSum < sum) {
                routeList[no] = coordList;
                maxSum = sum;
            }
            return;
        }

        Coord coord = coordList.get(coordList.size()-1);

        for (int i=0; i<4; i++) {
            int nr = coord.r + dr[i];
            int nc = coord.c + dc[i];
            if (nr<0 || n<=nr || nc<0 || n<=nc || visited[nr][nc]) {
                continue;
            }
            visited[nr][nc] = true;
            List<Coord> list = new ArrayList<>(coordList);
            list.add(new Coord(nr, nc, map[nr][nc]));

            // 이미 경로에 들어있는 경우 현재 sum에 0만 더함
            recur(no, cnt+1, sum + (isInRoute(list.get(list.size()-1), no) ? 0 : map[nr][nc]), list);
            visited[nr][nc] = false;
        }
    }

    public static boolean isInRoute(Coord curr, int no) {
        for (int i = 0; i < m; i++) {
            if (no == i) {
                continue;
            }
            for (Coord p : routeList[i]) {
                if (p.r == curr.r && p.c == curr.c) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class Coord {
        int r, c, sum;
        public Coord(int r, int c, int sum) {
            this.r = r;
            this.c = c;
            this.sum = sum;
        }
    }
}
