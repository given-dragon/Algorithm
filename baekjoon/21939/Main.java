import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // [난이도 L, 문제번호 P]
        TreeMap<Integer, TreeSet<Integer>> treeMap = new TreeMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            addProblem(treeMap, hashMap, st);
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            switch (command) {
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    int pNum = x==1 ? getHardProblem(treeMap) : getEasyProblem(treeMap);
                    sb.append(pNum).append('\n');
                    break;
                case "add":
                    addProblem(treeMap, hashMap, st);
                    break;
                case "solved":
                    int P = Integer.parseInt(st.nextToken());
                    Integer L = hashMap.remove(P);

                    TreeSet<Integer> pList = treeMap.get(L);
                    if (pList.size() == 1) {
                        treeMap.remove(L);
                    } else {
                        pList.remove(P);
                        treeMap.replace(L, pList);
                    }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static void addProblem(TreeMap<Integer, TreeSet<Integer>> treeMap, HashMap<Integer, Integer> hashMap, StringTokenizer st) {
        int P = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        hashMap.put(P, L);
        TreeSet<Integer> pList;
        if ((pList = treeMap.getOrDefault(L, null))!=null) {
            pList.add(P);
            treeMap.put(L, pList);
        } else {
            pList = new TreeSet<>();
            pList.add(P);
            treeMap.put(L, pList);
        }
    }
    private static Integer getHardProblem(TreeMap<Integer, TreeSet<Integer>> treeMap) {
        TreeSet<Integer> pList = treeMap.get(treeMap.lastKey());
        return pList.last();
    }
    private static Integer getEasyProblem(TreeMap<Integer, TreeSet<Integer>> treeMap) {
        TreeSet<Integer> pList = treeMap.get(treeMap.firstKey());
        return pList.first();
    }
}
