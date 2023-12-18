import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");

        int N = Integer.parseInt(s[0]); // 동전의 종류
        int K = Integer.parseInt(s[1]); // 가치의 합

        int[] coinArray = new int[N];
        for (int i = N-1; i >= 0; i--) {
            coinArray[i] = Integer.parseInt(br.readLine());
        }

        int minCoinCount = 0;
        int cursor = 0;
        while (K > 0) {
            int selectedCoin = coinArray[cursor++];

            if (selectedCoin > K) continue;

            int coinCount = K / selectedCoin;
            K %= selectedCoin;
            minCoinCount += coinCount;
        }

        bw.write(String.valueOf(minCoinCount));
        bw.flush();
        bw.close();
    }
}