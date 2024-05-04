import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    public static void main(String[] args) throws Exception {
        Map<Character, Integer> map = new HashMap<>();
        map.put('0', 0);map.put('1', 1);map.put('2', 2);
        map.put('3', 3);map.put('4', 4);map.put('5', 5);
        map.put('6', 6);map.put('7', 7);map.put('8', 8);
        map.put('9', 9);map.put('A', 10);map.put('B', 11);
        map.put('C', 12);map.put('D', 13);map.put('E', 14);
        map.put('F', 15);

        int[] pow = new int[7];
        pow[0] = 1;
        for (int i = 1; i < 7; i++) {
            pow[i] = pow[i-1] * 16;
        }

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            String line = br.readLine();

            Set<Integer> set = new HashSet<>();
            int n = N / 4;
            for (int start = 0; start < N-1; start++) {
                int p = start;
                int cnt = N;
                int x = 0;
                int num = 0;
                while (cnt-- > 0) {
                    if (--x < 0) {
                        x = n-1;
                        set.add(num);
                        num = 0;
                    }

                    char curr = line.charAt(p);
                    int decm = map.get(curr);
                    num += decm * pow[x];

                    p = (p + 1) % N;
                }
                set.add(num);
            }

            List<Integer> list = new ArrayList<>(set);
            list.sort(Comparator.reverseOrder());
            sb.append('#').append(tc).append(' ').append(list.get(K - 1)).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }
}