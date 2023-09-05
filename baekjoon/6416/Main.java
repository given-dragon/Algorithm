import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int k = 1;
        boolean hasDupVertex = false;
        Map<Integer, Integer> vertexInfo = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            while (!st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());

            int nodeU = Integer.parseInt(st.nextToken());
            int nodeV = Integer.parseInt(st.nextToken());

            if ((nodeU==-1) && (nodeV==-1))
                break;

            if ((nodeU == 0) && (nodeV == 0)) { // 출력
                boolean isTree = treeCheck(vertexInfo, nodes);
                sb.append("Case ").append(k++).append(" is ")
                        .append((!hasDupVertex&&isTree) ? "a tree." : "not a tree.").append('\n');

                // reset
                hasDupVertex = false;
                vertexInfo.clear();
                nodes.clear();
                continue;
            }

            nodes.add(nodeU);
            nodes.add(nodeV);
            Integer prevNode = vertexInfo.putIfAbsent(nodeV, nodeU);    // U -> V
            if (!hasDupVertex && prevNode!=null)
                hasDupVertex = true;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static boolean treeCheck(Map<Integer, Integer> vertexInfo, Set<Integer> nodes) {
        if (vertexInfo.size() == 0)
            return true;
        return ((nodes.size() - vertexInfo.size()) == 1);
    }
}