import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[] godArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            godArr[i] = Integer.parseInt(st.nextToken()) == 1 ? 1 : -1;
        }

        int leftMax = -1;
        int rightMin = Integer.MAX_VALUE;
        int currLeftSum = 0;
        int currRightSum = 0;
        for (int i = 0; i < N; i++) {
            currLeftSum = Math.max(godArr[i], currLeftSum + godArr[i]);
            leftMax = Math.max(leftMax, currLeftSum);

            currRightSum = Math.min(godArr[i], currRightSum + godArr[i]);
            rightMin = Math.min(rightMin, currRightSum);
        }

        System.out.println(Math.max(leftMax, -1*rightMin));
    }
}
