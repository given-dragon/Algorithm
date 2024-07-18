import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, L;
    static int[] A;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());

            if (!dq.isEmpty() && dq.getFirst() < i - L + 1) {
                dq.removeFirst();
            }

            while (!dq.isEmpty() && A[i] < A[dq.getLast()]) {
                dq.removeLast();
            }

            dq.addLast(i);
            sb.append(A[dq.getFirst()]).append(' ');
        }

        System.out.println(sb);
    }
}
