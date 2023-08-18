import java.io.*;
import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pQueue = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) return o1.compareTo(o2);
            else return Integer.compare(Math.abs(o1), Math.abs(o2));
        });

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num==0)
                sb.append(pQueue.isEmpty() ? 0 : pQueue.poll()).append('\n');
            else
                pQueue.add(num);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}