import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] prefixSum = new int[N][N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = prefixSum[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int sum = 0;
            for (int j = x1-1; j < x2; j++) {
                sum += prefixSum[j][y2] - prefixSum[j][y1 - 1];
            }
            sb.append(sum).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
/*
이번 코드는 한 행의 열을 더함 -> 실행시간 1200 ~ 1400 사이
예전 코드는 한 열의 행을 더함 -> 실행시간 900 ~ 1000 사이
무슨 차이인지? JVM 최적화 문제?
*/