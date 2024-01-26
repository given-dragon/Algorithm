import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 나무 수
        int M = Integer.parseInt(split[1]); // 필요한 길이

        int maxTreeHeight = 0;
        int[] treeArr = new int[N];
        split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            treeArr[i] = Integer.parseInt(split[i]);
            maxTreeHeight = Math.max(maxTreeHeight, treeArr[i]);
        }

        int lc = 0;
        int rc = maxTreeHeight-1;
        int result = 0;
        int cutterHeight;
        while (lc <= rc) {
            long treeHeightSum = 0L;
            cutterHeight = (lc+rc)/2;

            for (int tree : treeArr) {
                if (tree > cutterHeight) {
                    treeHeightSum += (tree - cutterHeight);
                }
            }

            if (treeHeightSum < M) {
                rc = cutterHeight-1;
                continue;
            }

            // treeHeightSum >= M
            lc = cutterHeight+1;
            result = cutterHeight;
        }

        System.out.println(result);
    }
}