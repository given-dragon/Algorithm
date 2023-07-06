import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
//        LinkedList<Integer> ll = new LinkedList<>();
        ArrayList<Integer> ll = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            ll.add(i);
        }

        sb.append("<");
        int idx = K-1;
        while (ll.size() > 1) {
            sb.append(ll.remove(idx)).append(", ");
            idx = (idx + K -1) % (ll.size());
        }
        sb.append(ll.remove(idx));
        sb.append(">");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}