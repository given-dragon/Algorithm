import java.io.*;

class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            count = 0;
            find(Integer.parseInt(br.readLine()));
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }

    public static void find(int num) {
        if (num < 0)
            return;
        if (num == 0)
            count++;

        find(num - 1);
        find(num - 2);
        find(num - 3);
    }
}