import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [풀이]
 * 1. map 초기화 하며 집의 좌표 저장
 *
 * 2. 배열을 순회
 * 2-1. 현재 좌표로부터 각 집까지의 맨해튼거리 계산 & 거리별로(0~40) 집 개수 저장
 * 2-2. 저장 배열로부터 최대 집 수 계산
 *      maxCnt = Math.max(maxCnt, 전체 집 수 - (먼 집부터 뺌))
 */
class Solution2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M, totalHomeCnt, maxHomeCnt;
    static int[] homeCntArr, minCostArr;
    static int[][] map;
    static List<int[]> homeList;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        minCostArr = new int[41];
        for (int K = 1; K <= 40; K++) {
            minCostArr[K] = K * K + (K - 1) * (K - 1);
        }
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            maxHomeCnt = 0;
            totalHomeCnt = 0;
            homeList = new ArrayList<>();

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j << 1);
                    if (map[i][j] == '1') {
                        homeList.add(new int[]{i, j});
                        totalHomeCnt++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int[] center = new int[]{i, j};
                    homeCntArr = new int[41];  // dist기준 homeCnt

                    for (int[] home : homeList) {
                        int dist = getDist(center, home)+1;
                        homeCntArr[dist]++;
                    }

                    int homeCnt = totalHomeCnt;
                    for (int K = 40; K > 0 ; K--) {
                        if (homeCnt * M < minCostArr[K]) {
                            homeCnt -= homeCntArr[K];
                            continue;
                        }
                        maxHomeCnt = Math.max(maxHomeCnt, homeCnt);
                        homeCnt -= homeCntArr[K];
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(maxHomeCnt).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }

    public static int getDist(int[] center, int[] home) {
        return Math.abs(center[0] - home[0]) + Math.abs(center[1] - home[1]);
    }
}