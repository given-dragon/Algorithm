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
            int test_caseNum = Integer.parseInt(sc.nextLine());
            int[] students = new int[101];

            String[] split = sc.nextLine().split(" ");
            for (String numStr : split) {
                int score = Integer.parseInt(numStr);
                students[score]++;
            }
            int maxStudentCount = 0;
            int maxScore = 0;
            for (int i = 0; i <= 100; i++) {
                if (maxStudentCount <= students[i]) {
                    maxStudentCount = students[i];
                    maxScore = i;
                }
            }
            sb.append('#').append(test_caseNum).append(' ').append(maxScore);
            System.out.println(sb);
        }
    }
}