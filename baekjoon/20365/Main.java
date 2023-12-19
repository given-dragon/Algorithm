import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String problemListup = br.readLine();

        int redCount = 0;
        int blueCount = 0;

        char beforeProblem = problemListup.charAt(0);
        if (beforeProblem == 'R') redCount++;
        else if(beforeProblem == 'B') blueCount++;

        for (int i = 1; i < N; i++) {
            char currentProblem = problemListup.charAt(i);

            // 같은 문제일 경우 하나로 묶기 위해 카운트 X
            if (beforeProblem == currentProblem) continue;

            if (currentProblem == 'R') redCount++;
            else if(currentProblem == 'B') blueCount++;

            beforeProblem = currentProblem;
        }

        int minFillCount = Math.min(redCount, blueCount) + 1;

        bw.write(String.valueOf(minFillCount));
        bw.flush();
        bw.close();
    }
}