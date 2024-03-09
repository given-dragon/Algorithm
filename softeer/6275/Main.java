import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    
    static int H, W;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H+1][W+1];

        Node point = null;
        for (int i=1; i<=H; i++) {
            String line = br.readLine();
            for (int j=1; j<=W; j++) {
                map[i][j] = line.charAt(j-1);
                if (point == null && map[i][j] == '#') {
                    point = new Node(i, j);
                }
            }
        }

        Node startPoint = getStartPoint(point);

        findDirAndCommand(startPoint);
        bw.append(sb);
        bw.flush();
    }

    public static Node getStartPoint(Node point) {
        boolean[][] visited = new boolean[H+1][W+1];

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(point);
        visited[point.r][point.c] = true;

        Node startPoint = null;
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            startPoint = target;

            for (int d=0; d<4; d++) {
                int nr = target.r + dr[d];
                int nc = target.c + dc[d];
                if (nr<1 || H<nr || nc<1 || W<nc || visited[nr][nc] || map[nr][nc]=='.') {
                    continue;
                }

                visited[nr][nc] = true;
                queue.add(new Node(nr, nc));
            }
        }

        return startPoint;
    }

    public static void findDirAndCommand(Node start) {
        sb.append(start.r).append(' ').append(start.c).append('\n');
        int startDir = getDir(start);
        sb.append(getDirChar(startDir)).append('\n');
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);

        int mvCnt = 0;
        int beforeDir = startDir;
        while (!queue.isEmpty()) {
            Node target = queue.poll();

            if (++mvCnt == 2) {
                mvCnt = 0;
                sb.append('A');
            }

            for (int d=0; d<4; d++) {
                int nr = target.r + dr[d];
                int nc = target.c + dc[d];
                if (nr<1 || H<nr || nc<1 || W<nc || map[nr][nc]=='.') {
                    continue;
                }

                if (beforeDir != d) {
                    if (beforeDir == 0) {
                        sb.append(d==3 ? 'R' : 'L');
                    }
                    else if (beforeDir == 1) {
                        sb.append(d==3 ? 'L' : 'R');
                    }
                    else if (beforeDir == 2) {
                        sb.append(d==1? 'L' : 'R');
                    }
                    else if (beforeDir == 3) {
                        sb.append(d==1 ? 'R' : 'L');
                    }
                    beforeDir = d;
                }

                map[nr][nc] = '.';
                queue.add(new Node(nr, nc));
                break;
            }
        }
    }

    public static int getDir(Node start) {
        for (int d=0; d<4; d++) {
            int nr = start.r + dr[d];
            int nc = start.c + dc[d];
            if (nr<1 || H<nr || nc<1 || W<nc) {
                continue;
            }

            if (map[nr][nc]=='#') {
                return d;
            }
        }
        return 4;
    }

    public static char getDirChar(int d) {
        switch(d) {
            case 0:
                return '^';
            case 1:
                return 'v';
            case 2:
                return '<';
            case 3:
                return '>';
        }
        return 'E';
    }

    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

