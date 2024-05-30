import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] snowArr;
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        snowArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snowArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snowArr);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i+3; j < N; j++) {
                int snowman1 = snowArr[i] + snowArr[j];

                int lc = i + 1;
                int rc = j - 1;
                while (lc < rc) {

                    int snowman2 = snowArr[lc] + snowArr[rc];
                    minDiff = Math.min(minDiff, Math.abs(snowman2 - snowman1));

                    if (snowman1 < snowman2) {
                        rc--;
                        continue;
                    }
                    if (snowman1 > snowman2) {
                        lc++;
                        continue;
                    }

                    break;
                }
            }
        }

        System.out.println(minDiff);
    }
}
