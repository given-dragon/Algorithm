import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 수열의 길이
        int K = Integer.parseInt(split[1]); // 최대 K번 삭제
        int[] seq = new int[N];

        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(split[i]);
        }

        int maxLen = 0;
        int evenCount =0;
        int oddCount = 0;
        int mainCsr = 0;
        int subCsr = 0;

        while (subCsr <= mainCsr) {
            if (mainCsr==N) break;

            if (oddCount <= K) {
                if (isEvenNum(seq[mainCsr])) evenCount++;
                else oddCount++;

                mainCsr++;
                maxLen = Math.max(maxLen, evenCount);
                continue;
            }

            // if (oddCount > K)
            if (isEvenNum(seq[subCsr])) evenCount--;
            else oddCount--;
            subCsr++;
        }

        System.out.println(maxLen);
    }
    public static boolean isEvenNum(int num) {
        return !((num & 1) == 1);
    }
}