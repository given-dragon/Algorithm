import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> drinks = new ArrayList<>();
        while (st.hasMoreTokens()) {
            drinks.add(Integer.parseInt(st.nextToken()));
        }
        drinks.sort(Comparator.reverseOrder()); // 큰 음료부터 잡아야 최대값 출력 가능

        double left = drinks.get(0);
        for (int i = 1; i < N; i++) {
            int right = drinks.get(i);

            double max = Math.max(left, right);
            double min = Math.min(left, right);

            left = max + min/2;
        }

        bw.write(String.valueOf(left));
        bw.flush();
        bw.close();
    }
}