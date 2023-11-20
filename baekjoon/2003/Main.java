import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수열 크기
        int M = Integer.parseInt(st.nextToken());   // 합을 구하는 횟수

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for (int i = 1; i <= N; i++) {
            if (arr[i] >= M) {
                idx = i;
                break;
            }
        }

        int count = 0;
        int target;
        for (int i = idx; i <= N; i++) {
            target = arr[i] - M;
            for (int j = 0; j < i; j++) {
                if (target == arr[j]) {
                    count++;
                    break;
                }
            }
        }

        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}