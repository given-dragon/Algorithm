import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        int[] arrA = new int[N];
        split = br.readLine().split(" ");
        initArr(N, arrA, split);

        int[] arrB = new int[M];
        split = br.readLine().split(" ");
        initArr(M, arrB, split);

        int curA = 0; int curB = 0;

        while (curA < N && curB < M) {
            if (arrA[curA] < arrB[curB]) {
                sb.append(arrA[curA]);
                curA++;
            } else {
                sb.append(arrB[curB]);
                curB++;
            }
            sb.append(' ');
        }

        if (curA < N) {
            for (int i = curA; i < N; i++) {
                sb.append(arrA[i]).append(' ');
            }
        } else {
            for (int i = curB; i < M; i++) {
                sb.append(arrB[i]).append(' ');
            }
        }

        System.out.println(sb);
    }

    private static void initArr(int M, int[] arrB, String[] split) {
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(split[i]);
        }
    }
}