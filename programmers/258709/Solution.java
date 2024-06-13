import java.util.*;

class Solution {
    int[][] diceArr;
    int diceCnt, groupSize;
    boolean[] selected;
    List<GameResult> gameResultList = new ArrayList<>();
    List<Integer>[] rollResult = new ArrayList[2];

    public int[] solution(int[][] dice) {
        diceArr = dice;
        diceCnt = dice.length;
        groupSize = diceCnt >> 1;
        selected = new boolean[diceCnt];

        rollResult[0] = new ArrayList<>();
        rollResult[1] = new ArrayList<>();

        selectDice(0, 0);

        int[] answer = gameResultList.stream()
                .max(Comparator.comparingInt(res -> res.winCnt))
                .get().diceArr;

        for (int i = 0; i < groupSize; i++) {
            answer[i]++;
        }

        return answer;
    }

    public void selectDice(int cnt, int idx) {
        if (cnt == groupSize) {
            int[][] diceGroup = new int[2][groupSize];
            setGroup(diceGroup);

            for (int g = 0; g < 2; g++) {
                rollResult[g].clear();
                rollDice(0, 0, g, diceGroup[g]);
                Collections.sort(rollResult[g]);
            }

            int winCnt = 0;
            for (int sum : rollResult[0]) {
                winCnt += (binSearch(sum) + 1);
            }

            gameResultList.add(new GameResult(diceGroup[0], winCnt));
            return;
        }

        for (int i = idx; i < diceCnt; i++) {
            selected[i] = true;
            selectDice(cnt + 1, i + 1);
            selected[i] = false;
        }
    }

    private void setGroup(int[][] group) {
        int j1 = 0, j2 = 0;
        for (int i = 0; i < diceCnt; i++) {
            if (selected[i]) {
                group[0][j1++] = i;
                continue;
            }
            group[1][j2++] = i;
        }
    }

    public void rollDice(int cnt, int sum, int groupNum, int[] group) {
        if (cnt == groupSize) {
            rollResult[groupNum].add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            rollDice(cnt + 1, sum + diceArr[group[cnt]][i], groupNum, group);
        }
    }

    public int binSearch(int target) {
        int lc = 0;
        int rc = rollResult[1].size() - 1;

        while (lc < rc) {
            int mid = (lc + rc + 1) >> 1;

            if (rollResult[1].get(mid) >= target) {
                rc = mid - 1;
                continue;
            }

            lc = mid;
        }

        return rc;
    }

    static class GameResult {
        int[] diceArr;
        int winCnt;

        public GameResult(int[] diceArr, int winCnt) {
            this.diceArr = diceArr;
            this.winCnt = winCnt;
        }
    }
}