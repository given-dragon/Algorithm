import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int T = 10;
        for (int t=1; t<=T; t++) {
            int[] boxArr = new int[101];
            int maxDump = Integer.parseInt(br.readLine());
            String[] split = br.readLine().split(" ");
             
            for (String s : split) {
                int height = Integer.parseInt(s);
                boxArr[height]++;
            }
             
            int lc  = findLowerBoxHeight(boxArr, 1);
            int rc  = findHigherBoxHeight(boxArr, 100);
             
            for (int i=0; i<maxDump; i++) {      
                if (rc - lc <= 1) {
                    break;
                }
                 
                boxArr[rc]--;
                boxArr[rc-1]++;
                boxArr[lc]--;
                boxArr[lc+1]++;
                 
                lc = findLowerBoxHeight(boxArr, lc);
                rc = findHigherBoxHeight(boxArr, rc);
            }
             
            sb.append('#').append(t).append(' ').append(rc-lc).append('\n');
        }
        bw.write(sb.toString());
        bw.close();
    }
 
    private static int findHigherBoxHeight(int[] boxArr, int start) {
        for (int i=start; i>1; i--) {
            if (boxArr[i] != 0) {
                return i;
            }
        }
        return 1;
    }
 
    private static int findLowerBoxHeight(int[] boxArr, int start) {
        for (int i=start; i<=100; i++) {
            if (boxArr[i] != 0) {
                return i;
            }
        }
        return 100;
    }
}
