import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] visited;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 수빈
        K = Integer.parseInt(st.nextToken());   // 동생

        int max = 100_001;
        visited = new int[max];
        Arrays.fill(visited, max);

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.t));
        pq.add(new Point(N, 0));

        while (!pq.isEmpty()) {
            Point target = pq.poll();

            if (target.x == K) {
                System.out.println(target.t);
                break;
            }

            int mul = target.x << 1;
            if (mul < max && (target.t < visited[mul])) {
                visited[mul] = target.t;
                pq.add(new Point(mul, visited[mul]));
            }

            int sub = target.x - 1;
            if (sub >= 0 && (target.t + 1 < visited[sub])) {
                visited[sub] = target.t + 1;
                pq.add(new Point(sub, visited[sub]));
            }

            int add = target.x + 1;
            if (add < max && (target.t + 1 < visited[add])) {
                visited[add] = target.t + 1;
                pq.add(new Point(add, visited[add]));
            }
        }
    }

    static class Point {
        int x, t;
        public Point(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }
}
