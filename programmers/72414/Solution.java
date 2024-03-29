import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int playTime = time2Sec(play_time);
        int advTime = time2Sec(adv_time);
        
        StringTokenizer st;
        int[] video = new int[playTime+2];
        for (String log : logs) {
            st = new StringTokenizer(log, "-");
            int startWatch = time2Sec(st.nextToken());
            int endWatch = time2Sec(st.nextToken());
            video[startWatch]++;
            video[endWatch]--;
        }
        
        for (int i=0; i<=playTime; i++) {
            video[i+1] += video[i];
        }
        
        int lc = 0;
        int rc = advTime-1;
        long watchTime = 0;
        long maxWatchTime = 0;
        for (int i=lc; i<=rc; i++) {
            watchTime += video[i];
        }
        maxWatchTime = watchTime;
        
        int answerTime = lc;
        while (rc < playTime) {
            watchTime -= video[lc++];
            watchTime += video[++rc];
            if (maxWatchTime < watchTime) {
                maxWatchTime = watchTime;
                answerTime = lc;
            }
        }
        
        String answer = sec2Time(answerTime);
        return answer;
    }
    
    public static int time2Sec(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int H = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        return H*3600 + M*60 + S;
    }
    
    public static String sec2Time(int time) {
        int H = time/3600;
        time = time - H*3600;
        int M= time/60;
        time = time - M*60;
        int S = time;
        return String.format("%02d:%02d:%02d", H, M, S);
    }
}
