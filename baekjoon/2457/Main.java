import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] days = {0, 31, 31, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[][] dateArr = new int[12+1][31+1];
    static Flower[] flowerArr;
    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        int offset = 1;
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= days[i]; j++) {
                dateArr[i][j] = offset++;
            }
        }

        flowerArr = new Flower[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            flowerArr[i] = new Flower(dateArr[sm][sd], dateArr[em][ed]);
        }
        Arrays.sort(flowerArr, (f1, f2) -> {
            if (f1.start == f2.start) {
                return -1*Integer.compare(f1.end, f2.end);
            }
            return Integer.compare(f1.start, f2.start);
        });


        int selectCnt = 0;
        Flower beforeFlower = new Flower(dateArr[3][1], dateArr[3][1]);
        Flower candiFlower = new Flower(dateArr[3][1], dateArr[3][1]);
        int ptr = 0;
        while (beforeFlower.end < dateArr[12][1]) {
            boolean isSelected = false;

            for (int i = ptr; i < N; i++) {
                Flower curr = flowerArr[i];
                if (beforeFlower.end < curr.start) {
                    break;
                }

                if (candiFlower.end < curr.end) {
                    candiFlower = curr;
                    ptr = i+1;
                    isSelected = true;
                }
            }

            if (isSelected) {
                beforeFlower = candiFlower;
                selectCnt++;
                continue;
            }
            break;
        }

        if (beforeFlower.end < dateArr[12][1]) {
            selectCnt = 0;
        }
        System.out.println(selectCnt);
    }

    static class Flower {
        int start, end;
        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
