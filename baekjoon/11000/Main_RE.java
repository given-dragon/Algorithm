import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        Class[] classArr = new Class[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            classArr[i] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(classArr, Comparator.comparingInt(c -> c.S));

        PriorityQueue<Class> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.T));
        pq.add(classArr[0]);
        for (int i = 1; i < N; i++) {
            if (pq.peek().T <= classArr[i].S) {
                pq.poll();
            }
            pq.offer(classArr[i]);
        }
        System.out.println(pq.size());
    }

    static class Class {
        int S, T;
        public Class(int S, int T) {
            this.S = S;
            this.T = T;
        }
    }
}
