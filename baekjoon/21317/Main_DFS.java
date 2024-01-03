import java.io.*;

class Main {
    static int minEnergy = Integer.MAX_VALUE;
    static int N, K;
    static boolean superJumpUsed = false;
    static int[][] energy;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        energy = new int[N][2];
        for (int i = 1; i < N; i++) {
            String[] split = br.readLine().split(" ");
            energy[i][0] = Integer.parseInt(split[0]);
            energy[i][1] = Integer.parseInt(split[1]);
        }
        K = Integer.parseInt(br.readLine());

        dfs(1, 0);
        System.out.println(minEnergy);
    }

    public static void dfs(int stone, int currentEnergy) {
        if (stone > N || currentEnergy > minEnergy)
            return;
        if (stone == N) {
            minEnergy = currentEnergy;
            return;
        }

        // 작은 점프
        dfs(stone + 1, currentEnergy + energy[stone][0]);
        // 큰 점프
        dfs(stone + 2, currentEnergy + energy[stone][1]);

        if (!superJumpUsed) {   // 매우 큰 점프
            superJumpUsed = true;
            dfs(stone + 3, currentEnergy + K);
            superJumpUsed = false;
        }
    }
}
