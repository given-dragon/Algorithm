import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = '0' + br.readLine();
        String B = '0' + br.readLine();

        int[][] dp = new int[A.length()][B.length()];

        for (int ac = 1; ac < A.length(); ac++) {   // a cursor
            char a = A.charAt(ac);
            for (int bc = 1; bc < B.length(); bc++) {   // b cursor
                char b = B.charAt(bc);

                if (a == b) {
                    // A의 1열 문자, B의 2열 문자가 같다고 가정해보면
                    // A의 0열, B의 1열까지 몇 개의 문자가 같았는지 확인해야함.
                    dp[ac][bc] = dp[ac - 1][bc - 1] + 1;
                    continue;
                }

                // a != b
                // ABA & ABA의 경우를 생각해보면 결국 두 방향 다 확인해야 한다.
                dp[ac][bc] = Math.max(dp[ac - 1][bc], dp[ac][bc - 1]);
            }
        }

        System.out.println(dp[A.length()-1][B.length()-1]);
    }
}