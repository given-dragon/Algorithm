import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        List<Integer> ropes = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            ropes.add(Integer.parseInt(br.readLine()));
        }

        ropes.sort(Comparator.reverseOrder());

        int maxWeight = 0;
        for (int ropeIdx = 0; ropeIdx < n; ropeIdx++) {
            int ropeCount = ropeIdx+1;
            int currentWeight = ropes.get(ropeIdx) * ropeCount;

            maxWeight = Math.max(maxWeight, currentWeight);
        }

        sb.append(maxWeight);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}