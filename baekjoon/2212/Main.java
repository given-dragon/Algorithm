import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 센서 수
        int K = Integer.parseInt(br.readLine());    // 집중국 수

        int[] sensorPositions = new int[N];
        getSortedSensorPositions(br, sensorPositions);

        int[] distances = new int[N - 1];
        getSortedDistances(sensorPositions, distances);

        K = Math.min(N, K);
        int minSum = 0;
        for (int i = 0; i < N - K; i++) {
            minSum += distances[i];
        }

        System.out.println(minSum);
    }

    private static void getSortedSensorPositions(BufferedReader br, int[] sensorsPosition) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sensorsPosition.length; i++) {
            sensorsPosition[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensorsPosition);
    }

    private static void getSortedDistances(int[] sensorsPosition, int[] distances) {
        for (int i = 0; i < sensorsPosition.length - 1; i++) {
            distances[i] = sensorsPosition[i+1] - sensorsPosition[i];
        }
        Arrays.sort(distances);
    }
}