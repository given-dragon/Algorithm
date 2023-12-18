import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dist = new int[N-1];  // 도시 사이의 거리
        int[] oilPrice = new int[N-1];    // 도시의 기름값(마지막 도시는 필요 X)

        StringTokenizer distTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer oilTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(distTokenizer.nextToken());
            oilPrice[i] = Integer.parseInt(oilTokenizer.nextToken());
        }

        long totalPrice = 0;
        int minOilPrice = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            minOilPrice = Math.min(minOilPrice, oilPrice[i]);
            totalPrice += (long)minOilPrice * dist[i];
        }

        bw.write(String.valueOf(totalPrice));
        bw.flush();
        bw.close();
    }
}