import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Deque<long[]> queue = new ArrayDeque<>();
        boolean flag = false;
        int domNo = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            queue.offer(new long[]{x, y});
        }
        while (queue.peek()[1] > 0) {
            queue.offer(queue.poll());
        }
        queue.offer(queue.peek());

        List<Dom> domList = new ArrayList<>();
        long[] xy = queue.poll();
        long ySave = xy[1];
        for (int i=0; i<N; i++) {
            xy = queue.poll();
            if ((ySave > 0 && xy[1] < 0) || (ySave < 0 && xy[1] > 0)) {
                domList.add(new Dom(domNo, xy[0]));
                if (flag) {
                    domNo++;
                }
                flag = !flag;
            }
            ySave = xy[1];
        }

        domList.sort(Comparator.comparingLong(d -> d.x));

        int outerDomCount = 0;
        int singleDomCount = 0;
        Deque<Dom> stack = new ArrayDeque<>();

        boolean[] innerDom = new boolean[domNo];
        boolean[] hasDom = new boolean[domNo];
        for (Dom dom : domList) {
            if (stack.isEmpty()) {
                stack.addLast(dom);
                continue;
            }

            // 기존 봉우리 시작 != 새로운 봉우리 시작
            if (stack.peekLast().no != dom.no) {
                innerDom[dom.no] = true;
                hasDom[stack.peekLast().no] = true;
                stack.addLast(dom);
                continue;
            }

            int no = stack.pollLast().no;
            if (!innerDom[no]) {    // 바깥 돔
                outerDomCount++;
            }
            if (!hasDom[no]) {    // 내부에 돔이 없는 돔
                singleDomCount++;
            }
        }
        System.out.println(outerDomCount + " " + singleDomCount);
    }

    static class Dom{
        int no;
        long x;
        public Dom(int no, long x) {
            this.no = no;
            this.x = x;
        }
    }
}
