import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, totalPopulation, minDiff = Integer.MAX_VALUE;
    static int[] population, parent;
    static boolean[] isSelected;
    static final boolean RED = true, BLUE = false;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        population = new int[N+1];
        isSelected = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int adjCnt = Integer.parseInt(st.nextToken());
            while (adjCnt-- > 0) {
                int v = Integer.parseInt(st.nextToken());
                adjList[i].add(v);
            }
        }

        powerset(1, 0);
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    public static void powerset(int idx, int redPop) {
        if (idx == N+1) {
            int bluePop = totalPopulation - redPop;
            if (redPop == 0 || bluePop == 0 || minDiff < Math.abs(redPop - bluePop)) {
                return;
            }

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int parent=1; parent<=N; parent++) {
                for (int child : adjList[parent]) {
                    if (isSelected[parent] == isSelected[child]) {
                        union(parent, child);
                    }
                }
            }

            if (isConnected(RED) && isConnected(BLUE)) {
                minDiff = Math.abs(redPop - bluePop);
            }
            return;
        }

        isSelected[idx] = RED;
        powerset(idx+1, redPop + population[idx]);

        isSelected[idx] = BLUE;
        powerset(idx+1, redPop);
    }

    private static boolean isConnected(boolean flag) {
        int head = 0;
        for (int i = 1; i <= N; i++) {
            if (isSelected[i] != flag) {
                continue;
            }
            if (head == 0) {
                head = find(i);
                continue;
            }
            if (find(i) != head) {
                return false;
            }
        }
        return true;
    }

    public static void union(int v1, int v2) {
        parent[find(v2)] = find(v1);
    }

    public static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}
