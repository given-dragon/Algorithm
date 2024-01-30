import java.io.BufferedReader;
import java.io.InputStreamReader;
class Solution
{
    public static void main(String args[]) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++) {
              
            String targetCode = br.readLine();
            int changeCount = '0' == targetCode.charAt(0) ? 0 : 1;
             
            for (int i=1; i<targetCode.length(); i++) {
                if (targetCode.charAt(i) == targetCode.charAt(i-1)) {
                    continue;
                }
                changeCount++;
            }
            sb.append('#').append(test_case).append(' ')
                .append(changeCount).append('\n');
        }
        System.out.println(sb);
    }
}
