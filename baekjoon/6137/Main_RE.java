import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] S = new char[N];

        for (int i = 0; i < N; i++) {
            S[i] = br.readLine().charAt(0);
        }

        StringBuilder sb = new StringBuilder();
        int lc = 0;
        int rc = N-1;
        while (lc < rc) {
            if (S[lc] < S[rc]) {
                sb.append(S[lc++]);
                continue;
            }
            if (S[lc] > S[rc]) {
                sb.append(S[rc--]);
                continue;
            }

            // S[lc] == S[rc]
            int tlc = lc;
            int trc = rc;
            boolean isDup = true;
            StringBuilder tsb = new StringBuilder();
            while (tlc < trc) {
                if (S[tlc] != S[trc]) break;

                if (isDup && (S[tlc] == S[lc]) && (S[trc] == S[rc])) {
                    tsb.append(S[lc]);
                }
                else {
                    isDup = false;
                }

                tlc++; trc--;
            }

            sb.append(tsb);
            if (S[tlc] < S[trc]) {
                lc += tsb.length();
            }
            else {  // S[tlc] > S[trc]
                rc -= tsb.length();
            }
        }
        sb.append(S[lc]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.append(sb.charAt(i));
            if ((i+1) % 80 == 0) {
                bw.append('\n');
            }
        }
        bw.flush();
        bw.close();
    }
}