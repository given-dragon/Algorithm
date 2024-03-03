import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, maxResult = Integer.MIN_VALUE;
    static String exp;
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        exp = br.readLine()+"e";

        recur(0, 0, '+');

        System.out.println(maxResult);
    }

    public static void recur (int idx, int result, char beforeOper) {
        if (beforeOper == 'e') {
            maxResult = Math.max(maxResult, result);
            return;
        }

        int num1 = exp.charAt(idx) - '0';
        char oper = exp.charAt(idx + 1);

        // 괄호치지않고 pass
        recur(idx+2, calc(beforeOper, result, num1), oper);

        // 괄호치고 계산 후 pass
        if (idx + 4 <= exp.length()) {
            int num2 = exp.charAt(idx + 2) - '0';
            char nextOper = exp.charAt(idx + 3);
            int pre = calc(oper, num1, num2);
            recur(idx+4, calc(beforeOper, result, pre), nextOper);
        }
    }

    public static int calc(char oper, int num1, int num2) {
        switch (oper) {
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
            default:
                return 0;
        }
    }
}
