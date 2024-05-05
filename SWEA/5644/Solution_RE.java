import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int M, A;

    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    static int[] moveInfoA, moveInfoB;
    static Point userA, userB;
    static List<Charger> bcList;

    static Charger emptyCharger = new Charger(null, 0, 0);
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());   // 이동 시간
            A = Integer.parseInt(st.nextToken());   // BC 개수

            userA = new Point(1, 1);
            userB = new Point(10, 10);
            moveInfoA = new int[M+1];
            moveInfoB = new int[M+1];
            String strInfoA = br.readLine();
            String strInfoB = br.readLine();
            for (int i = 1; i <= M; i++) {
                moveInfoA[i] = strInfoA.charAt((i-1) << 1) - '0';
                moveInfoB[i] = strInfoB.charAt((i-1) << 1) - '0';
            }

            bcList = new ArrayList<>();
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                Point p = new Point(r, c);
                Charger bc = new Charger(p, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                bcList.add(bc);
            }

            int chargeSum = 0;

            for (int m = 0; m <= M; m++) {
                userA.r += dr[moveInfoA[m]];
                userA.c += dc[moveInfoA[m]];
                userB.r += dr[moveInfoB[m]];
                userB.c += dc[moveInfoB[m]];

                List<Charger> candiListA = new ArrayList<>();
                List<Charger> candiListB = new ArrayList<>();
                for (Charger bc : bcList) {
                    if (getDist(userA, bc.p) <= bc.range) {
                        candiListA.add(bc);
                    }
                    if (getDist(userB, bc.p) <= bc.range) {
                        candiListB.add(bc);
                    }
                }

                Collections.sort(candiListA);
                Collections.sort(candiListB);

                int sum = Math.max(getPowerSum(candiListA, candiListB), getPowerSum(candiListB, candiListA));

                chargeSum += sum;
            }
            sb.append('#').append(tc).append(' ').append(chargeSum).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    private static int getPowerSum(List<Charger> candiListBc1, List<Charger> candiListBc2) {
        Charger select1, select2;
        select1 = candiListBc1.isEmpty() ? emptyCharger : candiListBc1.get(0);
        select2 = emptyCharger;
        for (Charger bc : candiListBc2) {
            if (select1.equals(bc)) {
                continue;
            }
            select2 = bc;
            break;
        }
        return select1.power + select2.power;
    }

    public static int getDist(Point user, Point bc) {
        return Math.abs(user.r - bc.r) + Math.abs(user.c - bc.c);
    }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Charger implements Comparable<Charger>{
        Point p;
        int range, power;
        public Charger(Point p, int range, int power) {
            this.p = p;
            this.range = range;
            this.power = power;
        }

        @Override
        public int compareTo(Charger oc) {
            return -1*Integer.compare(this.power, oc.power);
        }
    }
}