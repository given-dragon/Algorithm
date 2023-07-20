import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int building = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek()[0] < building) {    // 왼편에 나보다 더 큰 빌딩이 나올때까지 팝
                stack.pop();
            }
            if (stack.isEmpty()) {  // 왼편에 현재 빌딩보다 높은 빌딩이 없을경우
                sb.append(0).append(' ');
                stack.push(new int[]{building, i+1});
            }

            if (stack.peek()[0] > building) {
                sb.append(stack.peek()[1]).append(' ');
                stack.push(new int[]{building, i+1});
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}