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
            String[] split = sc.nextLine().split(" ");
            int N = Integer.parseInt(split[0]);
            int M = Integer.parseInt(split[1]);
            int[] arrN = new int[N];
            int[] arrM = new int[M];
            setArr(arrN, sc.nextLine().split(" "));
            setArr(arrM, sc.nextLine().split(" "));

            int result = (N <= M) ? getResult(arrN, arrM) : getResult(arrM, arrN);

            sb.append('#').append(test_case).append(' ').append(result);
            System.out.println(sb);
        }
    }

    public static void setArr(int[] arr, String[] split) {
        int i = 0;
        for (String str : split) {
            arr[i++] = Integer.parseInt(str);
        }
    }

    public static int getResult(int[] arrA, int[] arrB) {   // A.len <= B.len
        int result = 0;
        for (int i = 0; i <= arrB.length - arrA.length; i++) {
            int candidate = getCandidate(arrA, arrB, i);
            result = Math.max(result, candidate);
        }
        return result;
    }

    private static int getCandidate(int[] arrA, int[] arrB, int bIdx) {
        int tempResult = 0;
        for (int num : arrA) {
            tempResult += num * arrB[bIdx++];
        }
        return tempResult;
    }
}