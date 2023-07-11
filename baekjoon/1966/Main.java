import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Queue<int[]> queue = new ArrayDeque<>();
        LinkedList<Integer> priorityList = new LinkedList<>();

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int docNumber = Integer.parseInt(st.nextToken());
            int chaseDoc = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < docNumber; i++) {
                int docPriority = Integer.parseInt(st.nextToken());
                queue.add(new int[]{i, docPriority});

                priorityList.add(docPriority);
            }
            priorityList.sort(Comparator.reverseOrder());

            int printCount = 0;
            while (!queue.isEmpty()) {
                int[] polledDoc = queue.peek();

                if (priorityList.getFirst() == polledDoc[1]) {
                    ++printCount;
                    if (polledDoc[0] == chaseDoc) {
                        sb.append(printCount).append('\n');
                        break;
                    }
                    queue.remove();
                    priorityList.removeFirst();
                } else {
                    queue.add(queue.poll());
                }
            }

            queue.clear();
            priorityList.clear();

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
