import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import static java.lang.System.exit;

public class Main {
    private static void checkString(char[] bracketArray) {
        Stack<Character> stack = new Stack<>();
        for (char bracket : bracketArray) {
            if (bracket == '(' || bracket == '[') {
                stack.push(bracket);
            } else {
                if (!stack.isEmpty()) {
                    char openBracket = stack.pop();
                    if (openBracket == '(' && bracket == ')') {
                        continue;
                    }
                    if (openBracket == '[' && bracket == ']') {
                        continue;
                    }
                }
                System.out.println(0);
                exit(0);
            }
        }
        if (!stack.isEmpty()) {
            System.out.println(0);
            exit(0);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] bracketArray = br.readLine().toCharArray();

        checkString(bracketArray);

        Stack<Object[]> stack = new Stack<>();
        for (char bracket : bracketArray) {
            if (bracket == '(') {
                stack.push(new Object[]{bracket, 2});
            } else if (bracket == '[') {
                stack.push(new Object[]{bracket, 3});
            } else {
                Object[] data = stack.pop();
                if ((char)data[0] != '-') {
                    stack.push(new Object[]{'-', data[1]});
                } else {
                    Object[] data2 = stack.pop();
                    stack.push(new Object[]{data[0], (int)data2[1] * (int)data[1]});
                }

                while (stack.size() > 1) {
                    Object[] temp1 = stack.pop();
                    Object[] temp2 = stack.pop();
                    if ((char) temp1[0] != '-' || (char) temp2[0] != '-') {
                        stack.push(temp2);
                        stack.push(temp1);
                        break;
                    }
                    stack.push(new Object[]{'-', (int) temp1[1] + (int) temp2[1]});
                }
            }
        }
        System.out.println((int)stack.pop()[1]);
    }
}
