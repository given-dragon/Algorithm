import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int H, K, R;
    static Queue<Integer>[][] tree;
    public static void main(String[] args) throws Exception{

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());  // 높이
        K = Integer.parseInt(st.nextToken());  // 말단 업무 수
        R = Integer.parseInt(st.nextToken());  // 업무 진행 날짜 수

        int totalNodeCnt = (int)Math.pow(2, H+1) - 1;
        int leafNodeCnt = (int)Math.pow(2, H);

        tree = new ArrayDeque[totalNodeCnt+1][2];
        for (int i=totalNodeCnt-leafNodeCnt; i>0; i--) {
            tree[i][0] = new ArrayDeque<>();
            tree[i][1] = new ArrayDeque<>();
        }

        for (int i=leafNodeCnt; i<=totalNodeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i][0] = new ArrayDeque<>();
            for (int k=K; k>0; k--) {
                tree[i][0].add(Integer.parseInt(st.nextToken()));
            }
        }

        int ans = 0;
        int time = 0;
        while (++time <= R) {
            int peek = ((time&1) == 1) ? 0 : 1;

            if (hasTask(1, peek)) {
                ans += tree[1][peek].poll();
            }

            for (int i=2; i<=totalNodeCnt-leafNodeCnt; i++) {
                if (hasTask(i, peek)) {
                    int task = tree[i][peek].poll();
                    tree[i>>1][i&1].add(task);
                }
            }

            for (int i=totalNodeCnt; i>=leafNodeCnt; i--) {
                if (hasTask(i, 0)) {
                    int task = tree[i][0].poll();
                    tree[i>>1][i&1].add(task);  // 왼쪽(짝수)자식 -> 0번, 오른쪽(홀수)자식 -> 1번
                }
            }
        }

        System.out.println(ans);
    }

    public static boolean hasTask(int num, int peek) {
        return !tree[num][peek].isEmpty();
    }
}

