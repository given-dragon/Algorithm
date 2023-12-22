import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            meetings[i] = new Meeting(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        Arrays.sort(meetings);

        Queue<Integer> pq = new PriorityQueue<>();  // 오름차순 우선순위 큐
        pq.add(meetings[0].end);
        for (int i = 1; i < N; i++) {
            Meeting curremtMeeting = meetings[i];

            if (pq.peek() <= curremtMeeting.start) {
                pq.poll();
                pq.add(curremtMeeting.end);
            }
            else {
                pq.add(curremtMeeting.end);
            }
        }

        System.out.println(pq.size());
    }

    static class Meeting implements Comparable<Meeting>{
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting m) {
            return Integer.compare(this.start, m.start);
        }
    }
}