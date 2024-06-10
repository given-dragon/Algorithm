import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] numArr;
    static List<Integer> incList;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        incList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int num : numArr) {
            int idx = findIndex(num);

            if (idx >= incList.size()) {
                incList.add(num);
                continue;
            }
            incList.set(idx, num);
        }

        System.out.println(incList.size());

    }

    public static int findIndex(int num) {
        int lc = 0;
        int rc = incList.size()-1;
        while (lc <= rc) {
            int mid = (lc + rc) >> 1;

            int tNum = incList.get(mid);
            if (num <= tNum) {
                rc = mid - 1;
                continue;
            }

            // num > tNum
            lc = mid + 1;
        }

        return lc;
    }
}
