import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static int EMPTY = -1;

    static int N, M, K, minWeight = Integer.MAX_VALUE;;
    static int[] railWeight, railOrder;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 레일의 개수
        M = Integer.parseInt(st.nextToken());  // 택배 바구니의 무게
        K = Integer.parseInt(st.nextToken());  // 시행 횟수

        railWeight = new int[N];
        railOrder = new int[N];
        Arrays.fill(railOrder, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            railWeight[i] = Integer.parseInt(st.nextToken());
        }

        calcRailOrder(0);
        System.out.println(minWeight);
    }

    static void calcRailOrder(int order) {
        if (order == N) {
            int weightSum = simulation();
            minWeight = Math.min(minWeight, weightSum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (railOrder[i] != EMPTY) {
                continue;
            }

            railOrder[i] = order;
            calcRailOrder(order + 1);
            railOrder[i] = EMPTY;
        }
    }

    private static int simulation() {
        int k = K;
        int bucket = 0;
        int weightSum = 0;
        int curr = 0;

        while (k > 0) {
            int railNo = railOrder[curr];
            int nextBucket = bucket + railWeight[railNo];

            if (nextBucket > M) {
                k--;
                weightSum += bucket;
                bucket = railWeight[railNo];
            } else {
                bucket = nextBucket;
            }
            curr = (curr + 1) % N;
        }
        return weightSum;
    }
}
