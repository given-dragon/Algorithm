class Solution {
    static int[][] userArr;
    static int[] emoticonArr;
    static int[] discountRateArr = {10, 20, 30, 40};
    static int[] selectedRateArr;
    static int n, m, maxMoneySum, maxPlusUserCount;
    public int[] solution(int[][] users, int[] emoticons) {
        userArr = users;
        emoticonArr = emoticons;
        n = users.length;
        m = emoticons.length;

        selectedRateArr = new int[m];

        dfs(0, 0);
        int[] answer = {maxPlusUserCount, maxMoneySum};
        return answer;
    }

    public void dfs(int cnt, int idx) {
        if (cnt == m) {
            // 계산로직
            int moneySum = 0;
            int plusUserCount = 0;
            for (int[] user : userArr) {    // 유저
                int tempMoneySum = 0;
                for (int i=0; i<m; i++) {   // 이모티콘
                    if (user[0] <= selectedRateArr[i]) {
                        tempMoneySum += (int) (emoticonArr[i] * ((100-selectedRateArr[i])*0.01));
                    }
                }
                if (user[1] <= tempMoneySum) {
                    plusUserCount++;
                    continue;
                }
                moneySum += tempMoneySum;
            }

            if (maxPlusUserCount < plusUserCount) {
                maxPlusUserCount = plusUserCount;
                maxMoneySum = moneySum;
                return;
            }
            if (maxPlusUserCount == plusUserCount) {
                maxMoneySum = Math.max(maxMoneySum, moneySum);
            }
            return;
        }

        for (int i=0; i<discountRateArr.length; i++) {
            selectedRateArr[idx] = discountRateArr[i];
            dfs(cnt+1, idx+1);
        }
    }
}