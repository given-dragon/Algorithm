import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int T;
        T=10;
        /*
           여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
        */
 
        int boardSize = 100;
        for(int test_case = 1; test_case <= T; test_case++) {
            int t = Integer.parseInt(br.readLine());    // 테스트 케이스 번호
             
            int[][] board = new int[boardSize][boardSize];
             
            for (int i=0; i<boardSize; i++) {    //init board
                String line = br.readLine();
                for (int j=0; j<boardSize; j++) {
                    board[i][j] = line.charAt(j<<1) - '0';
                }
            }
             
            int x = 0;
            for (int i=0; i<boardSize; i++) {
                if (board[boardSize-1][i] == 2) {
                    x = i;
                    break;
                }
            }
             
            int row = boardSize-1;
            int col = x;
             
            while (true) {
                row--;
                if (row == 0) {
                    break;
                }
                 
                int lcol = col-1;
                int rcol = col+1;
                 
                if (0 < lcol && board[row][lcol] == 1) {
                    while (1 < lcol && board[row][lcol-1] == 1) {
                        lcol--;
                    }
                    col = lcol;
                    continue;
                }
                 
                if (rcol < boardSize && board[row][rcol] == 1) {
                    while (rcol < boardSize-1 && board[row][rcol+1] == 1) {
                        rcol++;
                    }
                    col = rcol;
                    continue;
                }
                 
            }
             
            sb.append('#').append(test_case).append(' ').append(col).append('\n');
        }
         
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
