import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        String numStr = br.readLine();
        Stack<Character> stack = new Stack<>();
        stack.push(numStr.charAt(0));

        int removeCount = 0;
        for (int i = 1; i <N; i++) {
            char num = stack.peek();
            char nextNum = numStr.charAt(i);

            if (removeCount == K) {
                stack.push(nextNum);
                continue;
            }

            if (nextNum <= num) {
                stack.push(nextNum);
                continue;
            }

            // if (num < nextNum)
            while (!stack.isEmpty()) {
                num = stack.peek();
                if (nextNum <= num || removeCount==K) {
                    break;
                }
                stack.pop();
                removeCount++;
            }
            stack.push(nextNum);
        }

        Character[] characters = stack.toArray(new Character[N - K]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N-K; i++) {
            sb.append(characters[i]);
        }

        System.out.println(sb);
    }
}