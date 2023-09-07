import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N+1];

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (tree[a] == null)
                tree[a] = new ArrayList<>();
            if (tree[b] == null)
                tree[b] = new ArrayList<>();

            tree[a].add(b);
            tree[b].add(a);
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 1) // 단절점
                sb.append(tree[k].size() > 1 ? "yes" : "no");
            else    //단절선
                sb.append("yes");

            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}