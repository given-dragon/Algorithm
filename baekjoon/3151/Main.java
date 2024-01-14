import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] students = new int[N];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(students);

        long count = 0;
        for (int i = 0; i <= N-3; i++) {
            int fixedStudent = students[i];
            int wantSum = -fixedStudent;

            int rc = N-1;
            int lc = i + 1;

            while (lc < rc) {
                int sum = students[lc] + students[rc];

                if (sum < wantSum) {
                    lc++;
                } else if (sum > wantSum) {
                    rc--;
                } else {
                    // 정렬된 배열 -> 끝과 끝이 같다면 그 사이는 모두 같음
                    if (students[lc] == students[rc]) {
                        count += rc - lc;
                        lc++;
                    }
                    // 양 끝 커서부터 같은 개수만큼 count => 같이 곱하기
                    else {
                        int leftStudent = students[lc];
                        int lCount = 0;
                        while (students[lc] == leftStudent) {
                            lCount++;
                            if (students[++lc] != leftStudent)
                                break;
                        }

                        int rightStudent = students[rc];
                        int rCount = 0;
                        while (students[rc] == rightStudent) {
                            rCount++;
                            if (students[--rc] != rightStudent)
                                break;
                        }

                        count += (long) lCount * rCount;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
