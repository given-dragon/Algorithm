import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, K;
    static int[] candyArr;
    static List<Integer>[] relationArr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 아이들 수
        M = Integer.parseInt(st.nextToken());   // 친구 관계 수
        K = Integer.parseInt(st.nextToken());   // 공명 임계치

        candyArr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candyArr[i] = Integer.parseInt(st.nextToken());
        }

        relationArr = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            relationArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationArr[a].add(b);
            relationArr[b].add(a);
        }

        ArrayList<Group> groupArr = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            int peopleCnt = 1;
            int candyCnt = candyArr[i];
            visited[i] = true;

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int t = queue.poll();

                for (int friend : relationArr[t]) {
                    if (visited[friend]) {
                        continue;
                    }
                    queue.add(friend);
                    peopleCnt += 1;
                    candyCnt += candyArr[friend];
                    visited[friend] = true;
                }
            }

            groupArr.add(new Group(peopleCnt, candyCnt));
        }

        groupArr.sort(Comparator.comparingInt(g -> g.peopleCnt));

        // [n]명의 캔디 개수
        int[] candyDp = new int[K];
        for (Group group : groupArr) {
            if (group.peopleCnt > K) {
                continue;
            }
            for (int i = K-1; i >= group.peopleCnt; i--) {
                candyDp[i] = Math.max(candyDp[i], candyDp[i - group.peopleCnt] + group.candyCnt);
            }
        }

        System.out.println(Arrays.stream(candyDp).max().getAsInt());
    }

    static class Group {
        int peopleCnt, candyCnt;
        public Group(int peopleCnt, int candyCnt) {
            this.peopleCnt = peopleCnt;
            this.candyCnt = candyCnt;
        }
    }
}
