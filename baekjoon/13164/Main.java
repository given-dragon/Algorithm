import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] costs = new int[N-1];
        int currentStudentHeight = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N-1; i++) {
            int nextStudentHeight = Integer.parseInt(st.nextToken());
            costs[i] = nextStudentHeight - currentStudentHeight;

            currentStudentHeight = nextStudentHeight;
        }

        Arrays.sort(costs);

        int minCost = 0;
        for (int i = 0; i < N - K; i++) {
            minCost += costs[i];
        }

        System.out.println(minCost);
    }
}