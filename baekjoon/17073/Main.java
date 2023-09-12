import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());   // 노드 수
        int W = Integer.parseInt(st.nextToken());   // 고인 물의 양

        int[] vertexCount = new int[N + 1]; // 해당 노드의 간선 개수만 카운팅

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            vertexCount[U]++;
            vertexCount[V]++;
        }

        int leafCount = getLeafCount(vertexCount, N);
        sb.append((double)W / leafCount);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static int getLeafCount(int[] vertexCount, int nodeSize) {
        int leafCount = 0;
        for (int i = 2; i <= nodeSize; i++) {
            if (vertexCount[i] == 1)
                leafCount++;
        }
        return leafCount;
    }
}