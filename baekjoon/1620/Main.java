import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, String> intMap = new HashMap<>();
        Map<String, Integer> strMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            intMap.put(i, name);
            strMap.put(name, i);
        }

        for (int i = 0; i < M; i++) {
            String question = br.readLine();
            try {
                int parseInt = Integer.parseInt(question);
                sb.append(intMap.get(parseInt)).append('\n');
            } catch (Exception e) {
                sb.append(strMap.get(question)).append('\n');
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}