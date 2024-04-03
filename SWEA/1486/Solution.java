import java.util.*;
import java.io.*;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
     
    static int N, B, minRes;
    static int[] heightArr;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
         
        for (int tc=1; tc<=T; tc++) {
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            heightArr = new int[N];
            for (int i=0; i<N; i++) {
                heightArr[i] = Integer.parseInt(st.nextToken());
            }
             
            minRes = Integer.MAX_VALUE;
            recur(0, 0);
            sb.append('#').append(tc).append(' ').append(minRes-B).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }
     
    public static void recur(int idx, int res) {
        if (minRes <= res) {
            return;
        }
        if (N <= idx) {
            if (B <= res) {
                minRes = Math.min(minRes, res); 
            }
            return;
        }
 
        recur(idx+1, res);
        recur(idx+1, res+heightArr[idx]);
    }
}

