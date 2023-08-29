import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 정렬된 전체 문제 set
        TreeSet<ProbData> sortedProbSet = new TreeSet<>();

        //<알고리즘 G, ProbData>
        HashMap<Integer, TreeSet<ProbData>> algorithmMap = new HashMap<>();

        //<문제번호 P, [난이도 L, 알고리즘 G]>
        HashMap<Integer, Integer[]> probInfoMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            registProb(sortedProbSet, algorithmMap, probInfoMap, st);
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            int x;

            switch (command) {
                case "recommend":
                    int G = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    sb.append(getProblem(algorithmMap, x, G)).append('\n');;
                    break;
                case "recommend2":
                    x = Integer.parseInt(st.nextToken());
                    sb.append(getProblem2(sortedProbSet, x)).append('\n');;
                    break;
                case "recommend3":
                    x = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    sb.append(getProblem3(sb, sortedProbSet, x, L)).append('\n');;
                    break;
                case "add":
                    registProb(sortedProbSet, algorithmMap, probInfoMap, st);
                    break;
                case "solved":
                    int P = Integer.parseInt(st.nextToken());
                    Integer[] LnG = probInfoMap.remove(P);
                    int level = LnG[0];
                    int algorithm = LnG[1];

                    ProbData tempData = new ProbData(P, level);
                    sortedProbSet.remove(tempData);

                    TreeSet<ProbData> probList = algorithmMap.get(algorithm);
                    if (probList.size() == 1) {
                        algorithmMap.remove(algorithm);
                    } else {
                        probList.remove(tempData);
                        algorithmMap.replace(algorithm, probList);
                    }
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static int getProblem3(StringBuilder sb, TreeSet<ProbData> sortedProbList, int x, int L) {
        ProbData compareData = new ProbData(-1, L);
        ProbData ceilingData = (x==1) ? sortedProbList.ceiling(compareData) : sortedProbList.floor(compareData);
        return (ceilingData != null) ? ceilingData.problem : -1;
    }
    private static int getProblem2(TreeSet<ProbData> probSet, int x) {
        return (x == 1 ? probSet.last() : probSet.first()).problem;
    }
    private static int getProblem(HashMap<Integer, TreeSet<ProbData>> algorithmMap, int x, int G) {
        TreeSet<ProbData> probList = algorithmMap.get(G);
        return (x == 1 ? probList.last() : probList.first()).problem;
    }
    private static void registProb(TreeSet<ProbData> probSet, HashMap<Integer, TreeSet<ProbData>> algorithmMap, HashMap<Integer, Integer[]> probInfoMap, StringTokenizer st) {
        int P = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        probInfoMap.put(P, new Integer[]{L, G});

        ProbData probData = new ProbData(P, L);
        probSet.add(probData);

        TreeSet<ProbData> probList = algorithmMap.getOrDefault(G, null);
        if (probList == null) probList = new TreeSet<>();
        probList.add(probData);
        algorithmMap.put(G, probList);
    }
    public static class ProbData implements Comparable<ProbData> {
        int problem;
        int level;
        public ProbData(int problem, int level) {
            this.problem = problem;
            this.level = level;
        }
        @Override
        public int compareTo(ProbData o) {
            return ((this.level - o.level) == 0)
                    ? (this.problem - o.problem)
                    : (this.level - o.level);
        }
    }
}