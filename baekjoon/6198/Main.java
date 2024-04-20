import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static Deque<Integer> dq;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        dq = new ArrayDeque<>();

        long sum = 0;
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            while (!dq.isEmpty() && dq.peekLast() <= height) {
                dq.pollLast();
            }
            sum += dq.size();
            dq.add(height);
        }

        bw.append(String.valueOf(sum));
        bw.flush();
    }
}
