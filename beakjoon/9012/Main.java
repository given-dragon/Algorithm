import java.io.*;
import java.util.*;

public class Main {

    public boolean vpsCheck(Stack<Character> stack, String str) {
        if (str.charAt(0) == ')')
            return false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else if (stack.empty() && c == ')') {
                return false;
            } else {
                stack.pop();
            }

        }
        return stack.empty();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 입력
        int T = Integer.parseInt(br.readLine());

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < T; i++) {
            boolean vpsResult = vpsCheck(stack, br.readLine());
            stack.clear();

            sb.append(vpsResult ? "YES\n" : "NO\n");
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}