import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static boolean isAlphabet(char c) {
        return 64 < (int)c && (int)c < 91;
    }

    public static double calculate(double num2, double num1, char operator) {
        if (operator == '+') {
            return num1 + num2;
        } else if (operator == '-') {
            return num1 - num2;
        } else if (operator == '*') {
            return num1 * num2;
        } else { // (operator == '/')
            return num1 / num2;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Stack<Double> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        char[] postfixNotation = br.readLine().toCharArray();

        ArrayList<Double> numList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numList.add(Double.parseDouble(br.readLine()));
        }

        for (char c : postfixNotation) {
            if (isAlphabet(c)) {
                stack.push(numList.get(c-65));
            } else {
                double temp = calculate(stack.pop(), stack.pop(), c);
                stack.push(temp);
            }
        }

        sb.append(String.format("%.2f", stack.pop()));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
