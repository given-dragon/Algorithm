import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
 
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        for (int testCase=1; testCase<=10; testCase++) {
            int pwdLen = Integer.parseInt(br.readLine());
            ArrayList<String> originCiphertext = new ArrayList<>();
            for (String text : br.readLine().split(" ")) {
                originCiphertext.add(text);
            }
             
            int cmdLen = Integer.parseInt(br.readLine());
            String[] split = br.readLine().split(" ");
             
            int idx = 0;
            for (int cmdIdx = 0; cmdIdx<cmdLen; cmdIdx++) {
                char cmd = split[idx++].charAt(0);
                int x = Integer.parseInt(split[idx++]);
                int y = Integer.parseInt(split[idx++]);
                 
                ArrayList<String> addCiphertext = new ArrayList<>();
                for (int i=0; i<y; i++) {
                    addCiphertext.add(split[idx++]);
                }
                originCiphertext.addAll(x, addCiphertext);
            }
             
            sb.append('#').append(testCase).append(' ');
            for (int i=0; i<10; i++) {
                sb.append(originCiphertext.get(i)).append(' ');
            }
            sb.append('\n');
        }
         
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
