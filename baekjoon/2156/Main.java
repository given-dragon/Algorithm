import java.io.*;

class Main {
    static int[][] board;
    static long[][] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 포도주 잔 개수
        int[] glassArr = new int[n+2];
        int[] val = new int[n+2];

        for (int i = 1; i <= n; i++) {
            glassArr[i] = Integer.parseInt(br.readLine());
        }

        val[0] = 0;
        val[1] = glassArr[1];
        val[2] = val[1] + glassArr[2];
        for (int i = 3; i <= n; i++) {
            // i를 마시는 경우
            int drink = val[i - 3] + glassArr[i - 1] + glassArr[i]; // i-1를 마시는 경우
            int notDrink = val[i - 2] + glassArr[i];    // i-1를 마시지 않는 경우
            int tempMax = Math.max(drink, notDrink);

            // i를 마시지 않는 경우
            val[i] = Math.max(tempMax, val[i - 1]);
        }

        System.out.println(val[n]);
    }
}