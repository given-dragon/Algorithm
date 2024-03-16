import java.util.*;

class Solution {
    List<int[]> problemList;
    int[][] timeArr;
    int maxAlp, maxCop;
    
    public int solution(int alp, int cop, int[][] problems) {
        int rLen = problems.length;
        
        problemList = new ArrayList<>(rLen+2);
        problemList.add(new int[]{0, 0, 1, 0, 1});
        problemList.add(new int[]{0, 0, 0, 1, 1});
        for (int[] problem : problems) {
            problemList.add(problem);
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        timeArr = new int[Math.max(alp, maxAlp)+1][Math.max(cop, maxCop)+1];
        for (int[] line : timeArr) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        pq.add(new int[]{0, alp, cop}); //[time, currAlp, currCop]
        timeArr[alp][cop] = 0;
        
        int answer = 0;
        while(!pq.isEmpty()) {
            int[] target = pq.poll();
            
            if (timeArr[target[1]][target[2]] < target[0]) {
                continue;
            }
            
            if (target[1] >= maxAlp && target[2] >= maxCop) {
                answer = target[0];
                break;
            }
            
            for (int[] problem : problemList) {  // [0:alp_req, 1:cop_req, 2:alp_rwd, 3:cop_rwd, 4:cost]
                if (target[1] < problem[0] || target[2] < problem[1]) {
                    continue;
                }
                
                int nTime = target[0] + problem[4];
                int nAlp = Math.min(target[1] + problem[2], maxAlp);
                int nCop = Math.min(target[2] + problem[3], maxCop);
                if (nTime < timeArr[nAlp][nCop]) {
                    timeArr[nAlp][nCop] = nTime;
                    pq.add(new int[]{nTime, nAlp, nCop});
                }
            }
        }
        
        return answer;
    }
}
