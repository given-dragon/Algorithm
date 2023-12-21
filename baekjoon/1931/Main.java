import java.io.*;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            meetings[i] = new Meeting(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
        }

        Arrays.sort(meetings);

        int maxMeetingCount = 1;
        Meeting beforeMeeting = meetings[0];
        for (int i = 1; i < N; i++) {
            Meeting currentMeeting = meetings[i];
            if (currentMeeting.startTime < beforeMeeting.endTime)
                continue;

            maxMeetingCount++;
            beforeMeeting = currentMeeting;
        }

        System.out.println(maxMeetingCount);
    }

    static class Meeting implements Comparable<Meeting> {
        int startTime;
        int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Meeting meeting) {
            int result = Integer.compare(this.endTime, meeting.endTime);
            if (result == 0) {
                result = Integer.compare(this.startTime, meeting.startTime);
            }
            return result;
        }

        public String toString() {
            return startTime + ", " + endTime;
        }
    }

}