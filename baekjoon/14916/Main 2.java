import java.io.*;

public class Main { // DP로 푼 버전
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 5]; // idx 5까지 미리 계산하기 위해 +5
        arr[2] = 1; // 기본 동전 1개로 해결 가능
        arr[5] = 1;
        arr[4] = arr[2] + 1;    // i를 6부터 시작하기 위해 따로 계산

        for (int i = 6; i < n + 1; i++) {
            if (arr[i-2] != 0)
                arr[i] = arr[i - 2] + 1;
            if (arr[i-5] != 0)
                arr[i] = Math.min(arr[i], arr[i - 5] + 1);
        }

        sb.append(arr[n] != 0 ? arr[n] : -1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}