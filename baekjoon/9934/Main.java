import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        int kPow = (int) Math.pow(2, K);
        int nodeCount = kPow - 1;
        String[] buildingNum = new String[nodeCount];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; st.hasMoreTokens(); i++)
            buildingNum[i] = st.nextToken();

        int num = kPow*2;
        for (int i = 0; i < K; i++) {
            nodeCount = nodeCount/2;
            int idx = nodeCount;
            num = num/2;
            for (int j = 0; j < Math.pow(2, i); j++) {
                sb.append(buildingNum[idx]).append(' ');
                idx += num;
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}