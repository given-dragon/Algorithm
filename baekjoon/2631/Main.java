import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N;

    int[] lis;
    int lisLen;

    public void solve() throws Exception {
        N = Integer.parseInt(br.readLine());

        int[] line = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        lis = new int[N + 1];
        lisLen = 0;
        for (int i = 0; i < N; i++) {
            binarySearch(line[i]);
        }

        System.out.println(N - lisLen);
    }

    private void binarySearch(int num) {
        int low = 0;
        int high = lisLen;

        while (low < high) {
            int mid = (low + high) >> 1;

            if (num < lis[mid]) {
                high = mid;
            } else {  // num > lis[mid]
                low = mid + 1;
            }
        }

        lis[low] = num;
        if (low == lisLen) {
            lisLen++;
        }
        System.out.println("len: " + lisLen);
        System.out.println(Arrays.toString(lis));
    }
}
