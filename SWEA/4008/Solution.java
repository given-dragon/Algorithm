import java.util.*;
import java.io.*;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
     
    static int N, minRes, maxRes;
    static int[] numArr, operArr;
     
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
         
        for (int tc=1; tc<=T; tc++ ) {
             
            N = Integer.parseInt(br.readLine());
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            operArr = new int[4];
            for (int i=0; i<4; i++) {
                operArr[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            numArr = new int[N];
            for (int i=0; i<N; i++) {
                numArr[i] = st.nextToken().charAt(0)-'0';
            }
             
            minRes = Integer.MAX_VALUE;
            maxRes = Integer.MIN_VALUE;
             
            recur(1, numArr[0]);
            sb.append('#').append(tc).append(' ').append(maxRes-minRes).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }
     
    public static void recur(int idx, int res) {
        if (N <= idx) {
            minRes = Math.min(minRes, res);
            maxRes = Math.max(maxRes, res);
            return;
        }
         
        for (int i=0; i<4; i++) {
            if (operArr[i] <= 0) {
                continue;
            }
             
            operArr[i]--;
            recur(idx+1, calc(res, numArr[idx], i));
            operArr[i]++;
        }
         
    }
     
    public static int calc(int num1, int num2, int oper) {
        switch (oper) {
        case 0: // +
            return num1+num2;
        case 1: // -
            return num1-num2;
        case 2: // *
            return num1*num2;
        case 3: // /
            return num1/num2;
        default:
            return 0;
        }
    }
}

