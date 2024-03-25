import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        int[] scoreSum = new int[N];

        int[] result = new int[N];
        PriorityQueue<Score> pq = new PriorityQueue<>( (s1, s2) -> -1 * Integer.compare(s1.score, s2.score));
        for (int round=0; round<3; round++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int idx=0; idx<N; idx++) {
                int score = Integer.parseInt(st.nextToken());
                pq.add(new Score(idx, score));
                scoreSum[idx] += score;
            }

            setOrder(pq, result);
            printOrder(result);
        }

        // sum score
        for (int idx=0; idx<N; idx++) {
            pq.add(new Score(idx, scoreSum[idx]));
        }
        setOrder(pq, result);
        printOrder(result);

        bw.append(sb);
        bw.flush();
    }

    public static void printOrder(int[] result) {
        for (int order : result) {
            sb.append(order).append(' ');
        }
        sb.append('\n');
    }

    public static void setOrder(PriorityQueue<Score> pq, int[] result) {
        int order = 1;
        Score before = pq.poll();
        result[before.idx] = order++;
        while(!pq.isEmpty()) {
            Score target = pq.poll();
            result[target.idx] = (target.score == before.score) ? result[before.idx] : order;
            before = target;
            order++;
        }
    }

    static class Score {
        int idx, score;
        public Score(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
    }
}

