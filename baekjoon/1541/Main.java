import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] splittedExpression = br.readLine().split("-");

        int minSum = 0;

        // '-' 부호가 나오기 전 수식
        String[] strNums = splittedExpression[0].split("\\+");
        for (String strNum : strNums) {
            minSum += Integer.parseInt(strNum);
        }

        // '-' 부호 이후 수식
        for (int i = 1; i < splittedExpression.length; i++) {
            strNums = splittedExpression[i].split("\\+");
            for (String strNum : strNums) {
                minSum -= Integer.parseInt(strNum);
            }
        }

        bw.write(String.valueOf(minSum));
        bw.flush();
        bw.close();
    }
}