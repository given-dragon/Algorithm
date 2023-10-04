import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> pArr = new ArrayList<>(N);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            pArr.add(Integer.parseInt(st.nextToken()));
        }

        pArr.sort(Comparator.naturalOrder());

        int totalTime = 0;
        int waitTime = 0;
        for (int p : pArr) {
            waitTime = waitTime + p;
            totalTime += waitTime;
        }

        sb.append(totalTime);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}