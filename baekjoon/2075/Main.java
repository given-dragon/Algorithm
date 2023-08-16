import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                pQueue.add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i < N; i++) {
            pQueue.poll();
        }

        sb.append(pQueue.poll());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}