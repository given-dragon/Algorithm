import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int test_case=1; test_case<=T; test_case++) {
            int N = Integer.parseInt(br.readLine());
             
            String[] farm = new String[N];
            for (int i=0; i<N; i++) {
                farm[i] = br.readLine();
            }
             
            int center = N/2;
            int endPoint = center;
             
            int alpha = 0;
            int sum = 0;
            for (int i=0; i<N; i++) {
                endPoint += alpha;
                int startPoint = N - endPoint -1;
                 
                for (int j=startPoint; j<=endPoint; j++) {
                    sum += farm[i].charAt(j) - '0';
                }
                 
                alpha = (i<center) ? 1 : -1;
            }
             
            sb.append('#').append(test_case).append(' ').append(sum).append('\n');
        }
         
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
