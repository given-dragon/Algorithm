import java.io.*;

class Main {
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] argument = br.readLine().split(" ");

        long A = Integer.parseInt(argument[0]);
        long B = Integer.parseInt(argument[1]);

        calc(A, B, 1);  // 연산의 최솟값에 1을 더해 출력 -> 미리 count를 1로 설정

        if (minCount == Integer.MAX_VALUE) {
            minCount = -1;
        }

        bw.write(String.valueOf(minCount));
        bw.flush();
        bw.close();
    }

    public static void calc(long num, long target, int count) {
        if (num > target) {
            return;
        }
        if (num == target) {
            minCount = Math.min(minCount, count);
        }

        calc(num<<1, target, count+1);  // 2를 곱한다
        calc(num*10+1, target, count+1);    // 1을 수의 가장 오른쪽에 추가한다
    }
}