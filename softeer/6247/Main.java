import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int n, q;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());  // 차 개수
        q = Integer.parseInt(st.nextToken());  // 질의 수

        int[] feulEffArr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            int val = Integer.parseInt(st.nextToken());
            feulEffArr[i] = val;
        }
        Arrays.sort(feulEffArr);

        Map<Integer, Integer> feulEff2IdxMap = new HashMap<>();
        for (int i=0; i<n; i++) {
            feulEff2IdxMap.put(feulEffArr[i], i+1);  // 연비-인덱스 map
        }

        while (q-- > 0) {
            int m = Integer.parseInt(br.readLine());
            if (!feulEff2IdxMap.containsKey(m)) {
                sb.append(0);
            }
            else {
                int idx = feulEff2IdxMap.get(m);
                sb.append((idx-1)*(n-idx));
            }
            sb.append('\n');
        }
        bw.append(sb);
        bw.flush();
    }
}

