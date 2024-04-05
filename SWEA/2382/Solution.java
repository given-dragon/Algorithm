import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static List<GroupInfo> groupList;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // map size
            M = Integer.parseInt(st.nextToken());   // time
            K = Integer.parseInt(st.nextToken());   // group cnt

            groupList = new LinkedList<>();

            List<GroupInfo>[][] map = new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = new ArrayList<>();
                }
            }

            for (int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()); // 1:상 2:하 3:좌 4:우

                GroupInfo gInfo = new GroupInfo(r, c, cnt, dir);
                groupList.add(gInfo);
                map[r][c].add(gInfo);
            }

            while(M-- > 0) {

                // move group
                for (int i=groupList.size()-1; i>=0; i--) {
                    GroupInfo gInfo = groupList.get(i);

                    map[gInfo.r][gInfo.c].remove(gInfo);

                    moveGroup(gInfo);
                    if (isOutOfBound(gInfo.r, gInfo.c)) {
                        if (gInfo.cnt==1) {
                            groupList.remove(i);
                            continue;
                        }
                        gInfo.cnt = (gInfo.cnt >> 1);
                        gInfo.dir += (gInfo.dir&1)==1 ? 1 : -1;
                    }

                    map[gInfo.r][gInfo.c].add(gInfo);
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j].size() <= 1) {
                            continue;
                        }

                        map[i][j].sort(Comparator.comparingInt(g -> g.cnt));
                        int cntSum = 0;
                        for (int k = map[i][j].size() - 1; k > 0; k--) {
                            GroupInfo gInfo = map[i][j].get(k);
                            if (map[i][j].get(k - 1).cnt < gInfo.cnt) {
                                map[i][j].get(k - 1).dir = gInfo.dir;
                            }
                            cntSum += gInfo.cnt;

                            groupList.remove(gInfo);
                            map[i][j].remove(k);
                        }

                        map[i][j].get(0).cnt += cntSum;
                    }
                }

            }

            int cnt = 0;
            for (GroupInfo groupInfo : groupList) {
                cnt += groupInfo.cnt;
            }

            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    private static void moveGroup(GroupInfo gInfo) {
        switch (gInfo.dir) {
            case 1:
                gInfo.r--;
                break;
            case 2:
                gInfo.r++;
                break;
            case 3:
                gInfo.c--;
                break;
            case 4:
                gInfo.c++;
                break;
        }
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<1 || N-1<=r || c<1 || N-1<=c;
    }

    static class GroupInfo {
        int r, c, cnt, dir;
        public GroupInfo(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
}
