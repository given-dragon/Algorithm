import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solver().solve();
    }
}

class Solver {

    int N, cnt;
    Map<Character, Map> wordMap;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        wordMap = new HashMap<>();

        Map<Character, Map> tempMap;
        while (N-- > 0) {
            char[] word = br.readLine().toCharArray();

            tempMap = wordMap;
            for (char c : word) {
                if (tempMap.containsKey(c)) {
                    tempMap = tempMap.get(c);
                    continue;
                }

                tempMap.put(c, new HashMap<>());
                tempMap = tempMap.get(c);
            }
        }

        recur(wordMap);
        System.out.println(cnt);
    }

    private void recur(Map<Character, Map> nextMap) {
        if (nextMap.isEmpty()) {
            cnt++;
            return;
        }

        for (char c : nextMap.keySet()) {
            recur(nextMap.get(c));
        }
    }

}
