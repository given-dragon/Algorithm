import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        String[] numSave = {"", "", ""};
        int[] result = new int[3];

        int idx = 0;
        for (int i=0; i<dartResult.length(); i++) {
            char c = dartResult.charAt(i);

            if ('0'<=c && c<='9') {
                numSave[idx] += String.valueOf(c);
                continue;
            }

            switch (c) {
                case 'S':
                    result[idx] = Integer.parseInt(numSave[idx]);
                    break;
                case 'D':
                    result[idx] = (int)Math.pow(Integer.parseInt(numSave[idx]), 2);
                    break;
                case 'T':
                    result[idx] = (int)Math.pow(Integer.parseInt(numSave[idx]), 3);
                    break;
                case '#':
                    result[idx-1] *= -1;
                    continue;
                case '*':
                    int count = 2;
                    for (int j=idx-1; (j>=0) && (count--!=0); j--)
                        result[j] *= 2;
                    continue;
            }
            idx++;
        }
        answer = result[0] + result[1] + result[2];
        return answer;
    }
}