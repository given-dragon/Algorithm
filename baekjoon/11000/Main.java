import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            lectures[i] = new Lecture(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
        }

        // 시작 시간 순서대로 정렬
        Arrays.sort(lectures);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 우선순위 큐에 강의 종료시간 추가(큐는 빨리 끝나는 시간을 우선으로)
        // 이어서 진행 가능한 수업이면, poll하고 큐에 해당 강의 추가
        // 진행 불가능한 수업이면 큐에 추가
        // 한 강의실의 timeline에서 마지막 강의만 큐에 남게됨 -> 강의실의 수
        priorityQueue.add(lectures[0].t);
        for (int i = 1; i < N; i++) {
            Lecture current = lectures[i];

            if (priorityQueue.peek() > current.s) {
                priorityQueue.add(current.t);
            }
            else {
                priorityQueue.poll();
                priorityQueue.add(current.t);
            }
        }
        System.out.println(priorityQueue.size());
    }

    static class Lecture implements Comparable<Lecture> {
        int s;  // 시작 시간
        int t;  // 종료 시간
        public Lecture(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Lecture l) {
            return Integer.compare(this.s, l.s);
        }
    }
}