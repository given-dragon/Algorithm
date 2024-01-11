import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        split = br.readLine().split(" ");
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(split[i]);
        }

        int lc = 0;
        int rc = 0;
        int maxLen = 0;

        int evenCount = 0;
        int oddCount = 0;

        while (rc < N) {
            if (isOddNum(seq[rc++])) {
                oddCount++;
            }
            else {
                evenCount++;
                maxLen = Math.max(maxLen, evenCount);
            }

            while (K < oddCount) {
                if (isOddNum(seq[lc++])) {
                    oddCount--;
                }
                else {
                    evenCount--;
                }
            }
        }

        System.out.println(maxLen);
    }

    public static boolean isOddNum(int num) {
        return (num & 1) == 1;
    }
}
