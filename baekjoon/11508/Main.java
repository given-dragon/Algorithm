import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> costArr = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            costArr.add(Integer.parseInt(br.readLine()));
        }

        costArr.sort(Comparator.reverseOrder());

        int freeCount = N / 3;
        int totalCost = 0;
        for (int i = 0; i < N; i++) {
            totalCost += costArr.get(i);
        }

        for (int i = 1; i <= freeCount; i++) {
            totalCost -= costArr.get(i*3 - 1);
        }

        sb.append(totalCost);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}