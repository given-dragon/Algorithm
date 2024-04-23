import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int cnt;

    public static void main(String[] args) throws Exception {
        cnt = Integer.parseInt(br.readLine());

        Brick[] brickArr = new Brick[cnt];
        for (int i = 0; i < cnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            brickArr[i] = new Brick(i + 1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(brickArr, (b1, b2) -> -1 * Integer.compare(b1.area, b2.area));

        int maxVal = 0;
        int[] dp = new int[cnt];
        int[] parentIdx = new int[cnt];
        Arrays.fill(parentIdx, -1);
        for (int i = 0; i < cnt; i++) {
            dp[i] = brickArr[i].h;

            for (int j = 0; j < i; j++) {
                if (brickArr[i].w < brickArr[j].w) {
                    if (dp[i] < brickArr[i].h + dp[j]) {
                        dp[i] = brickArr[i].h + dp[j];
                        parentIdx[i] = j;
                    }
                }
            }
            maxVal = Math.max(maxVal, dp[i]);
        }

        int brickCnt = 0;
        for (int i = cnt-1; i >= 0; i--) {
            if (dp[i] != maxVal) {
                continue;
            }

            sb.append('\n');
            while (parentIdx[i] >= 0){
                sb.append(brickArr[i].no).append('\n');
                i = parentIdx[i];
                brickCnt++;
            }
            sb.append(brickArr[i].no).append('\n');
            brickCnt++;
            break;
        }

        bw.append(String.valueOf(brickCnt));
        bw.append(sb);
        bw.flush();

    }

    static class Brick {
        int no, area, h, w;
        public Brick(int no, int area, int h, int w) {
            this.no = no;
            this.area = area;
            this.h = h;
            this.w = w;
        }
    }
}
