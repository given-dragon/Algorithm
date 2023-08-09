import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>();

        ArrayList<int[]> circlePoints = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int center = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circlePoints.add(new int[]{center-r, i, 0});    // = (
            circlePoints.add(new int[]{center+r, i, 1});    // = )
        }

        circlePoints.sort((c1, c2) -> {
            if (c1[0] > c2[0]) return 1;
            else if (c1[0] < c2[0]) return -1;
            return 0;
        });

        for (int[] point : circlePoints) {
            if (stack.isEmpty()) {
                stack.push(point);
            } else {
                int[] tempPoint = stack.peek();
                if (tempPoint[1] == point[1]) {
                    stack.pop();
                } else if (tempPoint[2]==0 && point[2]==1) {
                    break;
                } else {
                    stack.push(point);
                }
            }
        }
        sb.append(stack.isEmpty() ? "YES" : "NO");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}