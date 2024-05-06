import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[] ticketPrice;
    static int[] plan;
    static int minPrice;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ticketPrice = new int[4];  // [1일, 1달, 3달, 1년]
            for (int i = 0; i < 4; i++) {
                ticketPrice[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            plan = new int[13];
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            minPrice = Integer.MAX_VALUE;
            recur(1, 0);
            sb.append('#').append(tc).append(' ').append(minPrice).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static void recur(int month, int priceSum) {
        if (priceSum >= minPrice) {
            return;
        }
        if (month > 12) {
            minPrice = priceSum;
            return;
        }

        recur(month+1, priceSum + plan[month]*ticketPrice[0]);
        recur(month+1, priceSum + ticketPrice[1]);
        recur(month+3, priceSum + ticketPrice[2]);
        recur(month+12, priceSum + ticketPrice[3]);
    }
}