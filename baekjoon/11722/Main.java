import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] seq;
    int seqLen;

    public void solve() throws Exception {
        N = Integer.parseInt(br.readLine());
        seq = new int[N + 1];
        seqLen = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            seqLen = binarySearch(num);
        }

        System.out.println(seqLen);
    }

    private int binarySearch(int num) {
        int low = 0;
        int high = seqLen;

        while (low < high) {
            int mid = (low + high) >> 1;

            if (num >= seq[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        seq[high] = num;
        if (high == seqLen) {
            seqLen++;
        }

        return seqLen;
    }
}
