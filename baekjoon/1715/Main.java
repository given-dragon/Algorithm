import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int costSum = 0;
        while (pq.size() > 1) {
            int cards1 = pq.poll();
            int cards2 = pq.poll();
            int cards3 = cards1 + cards2;

            costSum += cards3;
            pq.add(cards3);
        }

        System.out.println(costSum);
    }
}