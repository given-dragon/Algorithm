import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int X = Integer.parseInt(split[1]);

        int[] prefixSum = new int[N+1];
        split = br.readLine().split(" ");
        prefixSum[1] = Integer.parseInt(split[0]);
        for (int i = 2; i <= N; i++) {
            prefixSum[i] = Integer.parseInt(split[i-1]) + prefixSum[i-1];
        }

        int maxVisitors = 0;
        int period = 0;

        for (int i = X; i <= N; i++) {  // X가 5라면, 1~5일까지 합산
            int visitors = prefixSum[i] - prefixSum[i - X];
            if (maxVisitors <= visitors) {
                if (maxVisitors != visitors) period = 0;
                period++;
                maxVisitors = visitors;
            }
        }

        if (maxVisitors == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxVisitors);
            System.out.println(period);
        }
    }
}