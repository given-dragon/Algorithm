import java.io.*;
import java.util.StringTokenizer;

class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] land = new int[N];

        for (int i = 0; i < N; i++) {
            land[i] = Integer.parseInt(st.nextToken());
        }

        int maxHoneyIncome = 0;

        // 1. 벌통 - 벌 - 벌
        // 벌통을 가장 왼쪽에 두는것이 항상 최선
        int[] honeySum = getPrefixSum(land);

        // 첫 벌은 가장 오른쪽에 두는게 항상 최선
        // 첫 벌의 수입 = sum[firstBeePos] - land[firstBeePos] - land[secondBeePos]
        // 두 번째 벌의 수입 = sum[secondBeePos] - land[secondBeePos]
        // 벌의 총 수입 = 첫 벌의 수입 + 두 번째 벌의 수입
        // = sum[firstBeePos] - land[firstBeePos] + sum[secondBeePos] - 2*land[secondBeePos]

        int tempHoneyIncome = getMaxHoneyFromSideHoneycomb(honeySum, land);
        maxHoneyIncome = Math.max(maxHoneyIncome, tempHoneyIncome);

        // 2. 벌 - 벌통 - 벌
        // 이 경우 배열 양 끝에 벌이 있을 경우가 항상 최선 -> 양 끝 벌 고정, 벌통 움직이기
        tempHoneyIncome = getMaxHoneyFromMiddleHoneycomb(honeySum, land);
        maxHoneyIncome = Math.max(maxHoneyIncome, tempHoneyIncome);

        // 3. 벌 - 벌 - 벌통
        // 벌통을 가장 오른쪽에 두는것이 항상 최선
        // 계산 편의를 위해 누적합 반전 (1번 케이스처럼 계산)
        int[] reversedLand = new int[N];
        for (int i = 0; i < N; i++) {
            reversedLand[i] = land[N - 1 - i];
        }
        honeySum = getPrefixSum(reversedLand);

        tempHoneyIncome = getMaxHoneyFromSideHoneycomb(honeySum, reversedLand);
        maxHoneyIncome = Math.max(maxHoneyIncome, tempHoneyIncome);

        System.out.println(maxHoneyIncome);
    }

    private static int[] getPrefixSum(int[] land) {
        int[] honeySum = new int[N];
        honeySum[0] = land[0];
        for (int i = 1; i < N; i++) {
            honeySum[i] = honeySum[i-1] + land[i];
        }
        return honeySum;
    }

    private static int getMaxHoneyFromSideHoneycomb(int[] honeySum, int[] land) {
        int firstBeePos = N -1;
        int firstBeeIncome = honeySum[firstBeePos] - land[firstBeePos]; // - land[secondBeePos]는 추후 계산

        int maxHoneyIncome = 0;
        for (int secondBeePos = firstBeePos - 1; secondBeePos > 0; secondBeePos--) {
            int tempHoneyIncome = firstBeeIncome + honeySum[secondBeePos] - 2 * land[secondBeePos];
            maxHoneyIncome = Math.max(maxHoneyIncome, tempHoneyIncome);
        }

        return maxHoneyIncome;
    }

    private static int getMaxHoneyFromMiddleHoneycomb(int[] honeySum, int[] land) {
        int firstBeePos = 0;
        int secondBeePos = N-1;

        int totalSum = honeySum[N-1];

        int maxHoneyIncome = 0;
        for (int honeycombPos = firstBeePos + 1; honeycombPos < secondBeePos; honeycombPos++) {
            int firstBeeIncome = honeySum[honeycombPos] - land[firstBeePos];
            int secondBeeIncome = totalSum - honeySum[honeycombPos - 1] - land[secondBeePos];

            int tempHoneyIncome = firstBeeIncome + secondBeeIncome;
            maxHoneyIncome = Math.max(maxHoneyIncome, tempHoneyIncome);
        }

        return maxHoneyIncome;
    }
}