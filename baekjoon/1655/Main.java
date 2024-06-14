import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static PriorityQueue<Integer> maxHeap, minHeap;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>(Comparator.naturalOrder());

        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());

            if (!minHeap.isEmpty() && minHeap.peek() < num) {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }

            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();
            if (maxHeapSize - minHeapSize == 2) {
                minHeap.add(maxHeap.poll());
                sb.append(Math.min(maxHeap.peek(), minHeap.peek())).append('\n');
                continue;
            }

            int peek = ((maxHeapSize > minHeapSize) ? maxHeap : minHeap).peek();
            sb.append(peek).append('\n');
        }

        bw.append(sb);
        bw.flush();
    }
}
