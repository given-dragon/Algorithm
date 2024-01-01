import java.util.Scanner;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=Integer.parseInt(sc.nextLine());
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(sc.nextLine());
 
            int[][] arr = new int[N][N];
 
            for (int i =0; i < N; i++) {
                String[] split = sc.nextLine().split(" ");
                for (int j =0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(split[j]);
                }
            }
 
            int[][] arr90 = new int[N][N];
            rollArr(N, arr, arr90);
            int[][] arr180 = new int[N][N];
            rollArr(N, arr90, arr180);
            int[][] arr270 = new int[N][N];
            rollArr(N, arr180, arr270);
 
            sb.append('#').append(test_case).append('\n');
            for (int i = 0; i < N; i++) {
                setResultLine(sb, arr90[i]);
                setResultLine(sb, arr180[i]);
                setResultLine(sb, arr270[i]);
                sb.append('\n');
            }
 
            System.out.print(sb);
        }
    }
    private static void setResultLine(StringBuilder sb, int[] arr) {
        for (int num : arr) {
            sb.append(num);
        }
        sb.append(' ');
    }
    public static void rollArr(int N, int[][] arr, int[][] rolledArr) {
        for (int i =0; i < N; i++) {
            for (int j = N-1; j >= 0; j--) {
                rolledArr[j][N-1-i] = arr[i][j];
            }
        }
    }
}