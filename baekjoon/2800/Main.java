import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static void combination(List<int[]> arr, boolean[] visited, int start, int r, char[] formula, ArrayList<String> formulaComb) {
        if(r == 0) {
            print(arr, visited, formula, formulaComb);
        } else {
            for(int i = start; i < arr.size(); i++) {
                visited[i] = true;
                combination(arr, visited, i + 1, r - 1, formula, formulaComb);
                visited[i] = false;
            }
        }
    }
    public static void print(List<int[]> arr, boolean[] visited, char[] formula, ArrayList<String> formulaComb) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> idxList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!visited[i])
                continue;
            idxList.add(arr.get(i)[0]);
            idxList.add(arr.get(i)[1]);
        }

        idxList.sort(Integer::compareTo);
        int removeIdx = 0;
        for (int i = 0; i < formula.length; i++) {
            if (i == idxList.get(removeIdx)){
                if (removeIdx < idxList.size()-1)
                    removeIdx++;
                continue;
            }
            sb.append(formula[i]);
        }
        formulaComb.add(sb.toString());
    }

    private static List<int[]> extractIdx(char[] formula) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> bracketIndex = new ArrayList<>();
        for (int i = 0; i < formula.length; i++) {
            if (formula[i] == '(') {
                stack.push(i);
            } else if (formula[i] == ')') {
                bracketIndex.add(new int[]{stack.pop(), i});
            }
        }
        return bracketIndex;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] originFormula = br.readLine().toCharArray();

        List<int[]> bracketIndex = extractIdx(originFormula);

        boolean[] visited = new boolean[bracketIndex.size()];
        ArrayList<String> formulaComb = new ArrayList<>();

        for(int r = 1; r <= bracketIndex.size(); r++) {
            combination(bracketIndex, visited, 0, r, originFormula, formulaComb);
        }

        formulaComb.sort(String::compareTo);
        LinkedHashSet<String> formulaSet = new LinkedHashSet<>(formulaComb);

        for (String formula : formulaSet) {
            sb.append(formula).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}