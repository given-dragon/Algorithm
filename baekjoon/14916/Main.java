import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int coinCount = 0;
        while (n > 0) {
            if (n % 5 == 0) {
                coinCount += n/5;
                break;
            }
            n -= 2;
            coinCount++;
        }
        sb.append(n >= 0 ? coinCount : -1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}