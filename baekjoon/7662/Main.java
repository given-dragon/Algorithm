import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> Q = new TreeMap<>(Comparator.naturalOrder());

            int k = Integer.parseInt(br.readLine());

            for (int j = 0; j < k; j++) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");

                String operator = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (operator.equals("I")) {
                    Integer numCount = Q.getOrDefault(num, 0) + 1;
                    Q.put(num, numCount);
                } else {
                    if (Q.isEmpty()) continue;

                    int key = (num == -1) ? Q.firstKey() : Q.lastKey();

                    Integer val = Q.get(key);
                    if (val == 1)
                        Q.remove(key);
                    else
                        Q.put(key, val - 1);
                }
            }

            if (Q.isEmpty())
                sb.append("EMPTY").append('\n');
            else
                sb.append(Q.lastKey()).append(' ').append(Q.firstKey()).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}