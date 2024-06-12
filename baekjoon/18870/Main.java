import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] numArr;
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
            map.put(numArr[i], 0);
        }

        List<Integer> numList = new ArrayList<>(map.keySet());
        Collections.sort(numList);

        for (int i=0; i<numList.size(); i++) {
            int num = numList.get(i);
            map.put(num, i);
        }

        for (int num : numArr) {
            sb.append(map.get(num)).append(' ');
        }

        bw.append(sb);
        bw.flush();
    }
}
