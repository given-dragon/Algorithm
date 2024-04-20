import java.util.*;

class Solution {
    int[] cnt = {1, 3, 7, 15, 31, 63};
    boolean[] bit;

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, 1);

        for (int n=0; n<numbers.length; n++) {
            long number = numbers[n];
            if (number == 0) {
                answer[n] = 0;
                continue;
            }

            bit = new boolean[64];

            int maxIdx = 1;
            long cursor = 1;
            if ((number & (cursor)) > 0) {
                bit[0] = true;
            }
            for (int i=1; i<64; i++) {
                cursor = cursor<<1;
                if ((number & cursor) > 0) {
                    bit[i] = true;
                    maxIdx = i+1;
                }
            }

            for (int c : cnt) {
                if (c>=maxIdx) {
                    maxIdx = c;
                    break;
                }
            }
            recur(0, maxIdx);

            for (boolean isNotChanged : bit) {
                if (isNotChanged) {
                    answer[n] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    public void recur(int l, int r) {
        int mid = (l+r)>>1;
        if ((l>r) || (!bit[mid])) {
            return;
        }

        bit[mid] = false;
        recur(l, mid-1);
        recur(mid+1, r);
    }
}