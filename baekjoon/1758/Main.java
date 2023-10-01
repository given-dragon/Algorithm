import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static final int INF = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        List<Integer> originTipList = new ArrayList<>(N+1);
        originTipList.add(INF);
        for (int i = 1; i <= N; i++) {
            originTipList.add(Integer.parseInt(br.readLine()));
        }

        originTipList.sort(Comparator.reverseOrder());

        long  maxTip = 0;
        for (int i = 1; i <= N; i++) {
            int finalTip = calcTip(originTipList.get(i), i);

            if (finalTip > 0) maxTip+=finalTip;
            else break;
        }

        sb.append(maxTip);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int calcTip(int originTip, int order) {
        return originTip - (order - 1);
    }
}