import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        int[] sequence = new int[N];
        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(split[i]);
        }

        int[] dupCheck = new int[100001];
        int len = 0;
        int maxLen = 0;
        int head = 0;
        int tail = 0;

        while (head < N) {
            int appendNum = sequence[head++];
            dupCheck[appendNum]++;

            while (dupCheck[appendNum] > K) {
                int removeNum = sequence[tail++];
                dupCheck[removeNum]--;
                len--;
            }
            maxLen = Math.max(maxLen, ++len);
        }
        System.out.println(maxLen);
    }
}