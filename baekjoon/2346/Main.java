import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int forward(int[] balloonList, int currentIdx, int moveCount) {
        int nextIdx = currentIdx;
        while (moveCount > 0) {
            nextIdx = (nextIdx + 1) % balloonList.length;

            if (balloonList[nextIdx] != 0){
                moveCount--;
            }
        }
        return nextIdx;
    }
    public static int backward(int[] balloonList, int currentIdx, int moveCount) {
        int nextIdx = currentIdx;
        while (moveCount < 0) {
            nextIdx = (nextIdx - 1 + balloonList.length) % balloonList.length;

            if (balloonList[nextIdx] != 0){
                moveCount++;
            }
        }
        return nextIdx;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] balloonList = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            int moveCount = Integer.parseInt(st.nextToken());
            balloonList[i] = moveCount;
        }

        int currentIdx = 0;
        for (int i=1; i<N; i++) {
            sb.append(currentIdx + 1).append(' ');

            int moveCount = balloonList[currentIdx];
            balloonList[currentIdx] = 0;

            if (moveCount > 0) {
                currentIdx = forward(balloonList, currentIdx, moveCount);
            } else {
                currentIdx = backward(balloonList, currentIdx, moveCount);
            }
        }
        sb.append(currentIdx + 1).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
