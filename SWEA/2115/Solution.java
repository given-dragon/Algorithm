import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[][] map;
    static int N, M, C, currMax, maxSc1, maxSc2;
    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 맵 크기
            M = Integer.parseInt(st.nextToken());   //선택할 수 있는 벌통의 개수
            C = Integer.parseInt(st.nextToken());   // 최대 채취 수

            map = new int[N][N];
            for (int i=0; i<N; i++) {
                String line = br.readLine();
                for (int j=0; j<N; j++) {
                    map[i][j] = line.charAt(j<<1)-'0';
                }
            }

            int answer = 0;
            // 일꾼1 위치 선택
            for (int i=0; i<N; i++) {
                for (int j=0; j<N-(M-1); j++) {
                    currMax = 0;

                    // 일꾼 1의 최대 점수 얻기
                    findMaxScore(i, j, j, 0, 0);
                    maxSc1 = currMax;


                    // 일꾼2 위치 선택
                    for (int i2=i; i2<N; i2++) {
                        // 같은열이면 j+M
                        // 다른열이면 0
                        int j2 = (i2==i) ? j+M : 0;
                        for (; j2<N-(M-1); j2++) {
                            currMax = 0;

                            // 일꾼 2의 최대 점수 얻기
                            findMaxScore(i2, j2, j2, 0, 0);
                            maxSc2 = currMax;

                            answer = Math.max(answer, maxSc1+maxSc2);
                        }
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void findMaxScore(int r, int c, int idx, int currSum, int currScore) {
        if (currSum > C) {
            return;
        }
        if (idx >= c+M) {
            currMax = Math.max(currMax, currScore);
            return;
        }

        currMax = Math.max(currMax, currScore);

        // 선택하는 경우
        findMaxScore(r, c, idx+1, currSum+map[r][idx], currScore+(map[r][idx] * map[r][idx]));

        // 선택하지 않는 경우
        findMaxScore(r, c, idx+1, currSum, currScore);
    }
}