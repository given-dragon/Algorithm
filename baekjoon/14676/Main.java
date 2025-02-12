import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, K;
    List<Integer>[] relationships;
    int[] inDegree;
    int[] buildingCnts;

    public void solve() throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 건물 종류 수
        M = Integer.parseInt(st.nextToken());  // 건물 사이 관계
        K = Integer.parseInt(st.nextToken());  // 영우의 게임 정보의 개수

        relationships = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            relationships[i] = new ArrayList<>();
        }

        inDegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            relationships[X].add(Y);  // X -> Y  X를 지으면 Y를 지을 수 있다.
            inDegree[Y]++;
        }

        buildingCnts = new int[N + 1];

        while (K-- > 0) {
            String line = br.readLine();
            char cmd = line.charAt(0);
            int bNum = Integer.parseInt(line.substring(2));

            boolean result = play(cmd, bNum);
            if (!result) {
                System.out.println("Lier!");
                return;
            }
        }

        System.out.println("King-God-Emperor");
    }

    private boolean play(char cmd, int bNum) {
        switch (cmd) {
            case '1':
                return build(bNum);
            case '2':
                return destroy(bNum);
        }
        return false;
    }

    private boolean build(int bNum) {
        if (!isBuildable(bNum)) {
            return false;
        }

        if (buildingCnts[bNum]++ == 0) {
            relationships[bNum].forEach(child -> inDegree[child]--);
        }
        return true;
    }

    private boolean isBuildable(int bNum) {
        return inDegree[bNum] == 0;
    }

    private boolean destroy(int bNum) {
        if (buildingCnts[bNum] == 0) {
            return false;
        }

        if (--buildingCnts[bNum] == 0) {
            relationships[bNum].forEach(child -> inDegree[child]++);
        }
        return true;
    }
}
