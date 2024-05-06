import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int truthCnt;
    static int[] truthArr;
    static int[] parent;
    static int[][] partyArr;
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 사람 수
        M = Integer.parseInt(st.nextToken());  // 파티 수

        st = new StringTokenizer(br.readLine());
        truthCnt = Integer.parseInt(st.nextToken());
        if (truthCnt == 0) {
            bw.append(String.valueOf(M));
            bw.flush();
            return;
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        truthArr = new int[truthCnt];
        truthArr[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < truthCnt; i++) {
            truthArr[i] = Integer.parseInt(st.nextToken());
            union(truthArr[0], truthArr[i]);
        }

        partyArr = new int[M][];
        for (int partyNo=0; partyNo<M; partyNo++) {
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            int[] party = new int[cnt];

            party[0] = Integer.parseInt(st.nextToken());
            for (int i=1; i<cnt; i++) {
                party[i] = Integer.parseInt(st.nextToken());
                union(party[0], party[i]);
            }

            partyArr[partyNo] = party;
        }

        int result = 0;
        for (int[] party : partyArr) {
            int pCnt = 0;
            for (int p : party) {
                if (find(truthArr[0]) == find(p)) {
                    break;
                }
                pCnt++;
            }
            if (party.length == pCnt) {
                result++;
            }
        }

        bw.append(String.valueOf(result));
        bw.flush();
    }

    public static void union(int node1, int node2) {
        int no1 = find(node1);
        int no2 = find(node2);
        parent[no2] = no1;
    }
    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
}
