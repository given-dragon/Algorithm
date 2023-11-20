import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 표 크기
        int M = Integer.parseInt(st.nextToken());   // 합을 구하는 횟수

        int[][] arr = new int[N+1][N];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = arr[i - 1][j] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int sum = 0;
            for (int j = y1-1; j <y2; j++) {
                sum += arr[x2][j] - arr[x1-1][j];
            }
            sb.append(sum).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}