import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            Queue<Long> pq = new PriorityQueue<>();
            for (String token : br.readLine().split(" ")) {
                pq.add(Long.parseLong(token));
            }

            long costSum = 0;
            while (pq.size() > 1) {
                long file1 = pq.poll();
                long file2 = pq.poll();

                long tempFile = file1 + file2;
                costSum += tempFile;    // 파일을 더할 때 생기는 cost 더하기

                pq.add(tempFile);   // 더해진 파일은 다시 PQ에 삽입
            }

            sb.append(costSum).append('\n');
        }
        System.out.println(sb);
    }
}