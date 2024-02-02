class Solution {
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int N = board.length;
        int M = board[0].length;

        // write skill result into empty map
        int[][] skillResultMap = new int[N+1][M+1];
        for (int i=0; i<skill.length; i++) {
            int type = skill[i][0]; // 1-공격, 2-회복
            int r1 = skill[i][1] + 1;
            int c1 = skill[i][2] + 1;
            int r2 = skill[i][3] + 1;
            int c2 = skill[i][4] + 1;
            int degree = skill[i][5];

            if (type==2) {  // reverse degree for compare-> attack:+, heal:-
                degree *= -1;
            }
            // mark skill degree -> area
            skillResultMap[r2][c2] += degree;    // right bottom
            skillResultMap[r1-1][c2] += -1 * degree;  // right top
            skillResultMap[r2][c1-1] += -1 * degree;  // left bottom
            skillResultMap[r1-1][c1-1] += degree; // left top
        }

        // sum left -> right
        for (int i=N; i>0; i--) {
            for (int j=M-1; j>0; j--) {
                skillResultMap[i][j] += skillResultMap[i][j+1];
            }
        }

        // sum bottom -> top
        for (int i=M; i>0; i--) {
            for (int j=N-1; j>0; j--) {
                skillResultMap[j][i] += skillResultMap[j+1][i];
            }
        }

        // calc answer
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (board[i-1][j-1] <= skillResultMap[i][j]) {
                    continue;
                }
                answer++;
            }
        }

        return answer;
    }
}

