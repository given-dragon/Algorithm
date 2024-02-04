import java.io.*;

class Main {
    static StringBuilder sb;
    static int moveCount;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        move(1, 2, 3, 0);

        System.out.println(moveCount);
        System.out.println(sb);
    }

    public static void move(int from, int temp, int to, int cnt) {
        if (cnt == N) {
            return;
        }

        moveCount++;

        move(from, to, temp, cnt+1);
        sb.append(from).append(' ').append(to).append('\n');
        move(temp, from, to, cnt+1);
    }
}