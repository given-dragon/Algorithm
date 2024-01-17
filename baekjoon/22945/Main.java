import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");

        int[] line = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(split[i]);
        }

        int lc = 0;
        int rc = N-1;
        int maxVal = 0;
        while (lc < rc) {
            int between = rc-lc-1;
            int min = Math.min(line[lc], line[rc]);
            int val = between * min;
            maxVal = Math.max(maxVal, val);

            if (line[lc] < line[rc]) {
                lc++;
            }
            else if (line[lc] > line[rc]) {
                rc--;
            }
            else {    // line[lc] == line[rc
                lc++;
                rc--;
            }
        }
        System.out.println(maxVal);
    }
}
