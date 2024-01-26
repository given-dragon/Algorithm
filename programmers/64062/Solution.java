import java.util.*;

class Solution {
    public static int solution(int[] stones, int k) {

        int lc = 1;
        int rc = 200_000_000;

        int answer = 0;
        while (lc <= rc) {
            int maxNinizCount = (lc+rc)/2;  // middle

            int removedStoneCount = 0;
            int count = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] < maxNinizCount) {
                    count+=1;
                    removedStoneCount = Math.max(removedStoneCount, count);
                    continue;
                }
                count = 0;
            }

            if (removedStoneCount < k) {
                lc = maxNinizCount+1;
                answer = Math.max(answer, maxNinizCount);
                continue;
            }
            
            // removedStoneCount >= k
            rc = maxNinizCount-1;
        }

        return answer;
    }
}

