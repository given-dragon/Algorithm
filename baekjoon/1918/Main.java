import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String expr = br.readLine();
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char charAt = expr.charAt(i);

            if (65<=charAt && charAt<=90) {
                sb.append(charAt);
            } else if (charAt == '(') {
                operatorStack.push(charAt);
            } else if (charAt == ')') {
                while (operatorStack.peek() != '(')
                    sb.append(operatorStack.pop());
                operatorStack.pop();
            } else if (charAt == '*' || charAt == '/'){
                if (operatorStack.isEmpty() || (operatorStack.peek() == '+' || operatorStack.peek() == '-')) {
                    operatorStack.push(charAt);
                } else {
                    if (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        sb.append(operatorStack.pop());
                    }
                    operatorStack.push(charAt);
                }
            } else if (charAt == '+' || charAt == '-') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    sb.append(operatorStack.pop());
                }
                operatorStack.push(charAt);
            }
        }

        while (!operatorStack.isEmpty()) {
            sb.append(operatorStack.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}