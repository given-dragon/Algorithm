import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 물품 수
        int K = Integer.parseInt(split[1]); // 최대 무게

        Item[] items = new Item[N+1];
        for (int i = 1; i <= N; i++) {
            split = br.readLine().split(" ");
            items[i] = new Item(
                    Integer.parseInt(split[0]),
                    Integer.parseInt(split[1])
            );
        }

        int[][] dp = new int[N+1][K+1];
        for (int n = 1; n <= N; n++) {
            Item item = items[n];
            for (int k = 1; k <= K; k++) {

                if (k < item.weight) {
                    dp[n][k] = dp[n-1][k];
                    continue;
                }

                if (k == item.weight) {
                    dp[n][k] = Math.max(dp[n - 1][k], item.value);
                    continue;
                }

                // k > item.weight
                dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - item.weight] + item.value);
            }
        }
        System.out.println(dp[N][K]);
    }

    static class Item {
        int weight, value;
        public Item(int w, int v) {
            this.weight = w;
            this.value = v;
        }
    }
}