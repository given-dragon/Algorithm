import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void push(Stack<Integer> stack, int num, StringBuilder sb) {
        sb.append('+').append('\n');
        stack.push(num);
    }
    public static int pop(Stack<Integer> stack, StringBuilder sb) {
        if (stack.isEmpty())
            return 0;

        sb.append('-').append('\n');
        return stack.pop();
    }
    public static int sequence_process(Stack<Integer> stack, int targetNum, int currentNum, StringBuilder sb) {
        if (currentNum <= targetNum) {
            while (currentNum <= targetNum) {
                push(stack, currentNum++, sb);
            }
            pop(stack, sb);
            return currentNum;
        } else {
            int popedNum = pop(stack, sb);

            if (popedNum < targetNum) {
                sb.delete(0, sb.length());
                sb.append("NO").append('\n');
                return 0;
            }
            while (popedNum > targetNum) {
                popedNum = pop(stack, sb);
            }
            return currentNum;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());

        int targetNum;
        int currentNum = 1;
        for (int i = 0; i < n; i++) {
            targetNum = Integer.parseInt(br.readLine());
            currentNum = sequence_process(stack, targetNum, currentNum, sb);

            if (currentNum==0)
                break;
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }
}
