import java.io.*;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            lectures[i] = new Lecture(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
        }

        Arrays.sort(lectures);

        int minRoomCount = 0;  // 강의를 다 채웠을 때

        for (int i = 0; i < N; i++) {

            if (lectures[i].visited) continue;  // 이미 배정됐다면 패스
            Lecture before = lectures[i];   // 강의실을 처음 채우는 강의

            for (int j = i+1; j < N; j++) {
                Lecture current = lectures[j];

                if (current.visited) continue;  // 이미 배정됐다면 패스

                if (before.t > current.s) continue;
                before = current;
                current.visited = true;
            }
            minRoomCount++;
        }

        System.out.println(minRoomCount);
    }

    static class Lecture implements Comparable<Lecture> {
        int s;  // 시작 시간
        int t;  // 종료 시간
        boolean visited;
        public Lecture(int s, int t) {
            this.s = s;
            this.t = t;
            visited = false;
        }

        @Override
        public int compareTo(Lecture l) {
            int result = Integer.compare(this.t, l.t);
            if (result == 0) {
                result = Integer.compare(this.s, l.s);
            }
            return result;
        }
    }
}