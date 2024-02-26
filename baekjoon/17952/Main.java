import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Deque<Task> stack = new ArrayDeque<>();
        int score = 0;
        for (int n=1; n<=N; n++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());

            if (time == 0) {
                if (stack.isEmpty()) {
                    continue;
                }
            }
            else {
                int A = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                stack.offerLast(new Task(A, T));
            }

            if (--(stack.peekLast()).T==0) {
                score += stack.pollLast().A;
            }

        }
        System.out.println(score);
    }

    static class Task {
        int A, T;
        public Task(int A, int T) {
            this.A = A;
            this.T = T;
        }
    }
}