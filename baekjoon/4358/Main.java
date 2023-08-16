import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> treeMap = new TreeMap<>(Comparator.naturalOrder());
        double totalTree = 0.0;

        String tree;
        Integer count;
        while ((tree = br.readLine()) != null && tree.length()!=0) {
            totalTree++;
            if ((count = treeMap.putIfAbsent(tree, 1)) != null)
                treeMap.replace(tree, ++count);
        }
        treeMap.forEach((key, value) -> sb.append(key).append(' ').append(String.format("%.4f", value / totalTree * 100)).append('\n'));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}