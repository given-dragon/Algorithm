import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 컴퓨터 수
        int P = Integer.parseInt(br.readLine());    // 컴퓨터 쌍의 수

        ArrayList<Integer>[] info = init(N, P, br);

        boolean[] isVisited = new boolean[N+1];
        isVisited[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        int count = 0;
        while (!queue.isEmpty()) {
            int target = queue.poll();

            for (int cp : info[target]) {
                if (isVisited[cp]) continue;

                isVisited[cp] = true;
                queue.add(cp);
                count++;
            }
        }
        System.out.println(count);
    }

    private static ArrayList<Integer>[] init(int N, int P, BufferedReader br) throws IOException {
        ArrayList<Integer>[] info = new ArrayList[N +1];
        for (int i = 1; i <= N; i++) {
            info[i] = new ArrayList<>();
        }
        for (int i = 1; i <= P; i++) {
            String[] split = br.readLine().split(" ");
            int cp1 = Integer.parseInt(split[0]);
            int cp2 = Integer.parseInt(split[1]);
            info[cp1].add(cp2);
            info[cp2].add(cp1);
        }
        return info;
    }
}