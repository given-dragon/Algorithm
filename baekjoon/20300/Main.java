import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Long> muscleLossList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            muscleLossList.add(Long.parseLong(st.nextToken()));
        }
        muscleLossList.sort(Comparator.naturalOrder());

        long minM = 0;
        if ((N & 1) == 1) { // 운동기구의 개수가 홀수일 경우 우선 마지막을 최소로 설정
            minM = muscleLossList.get(N - 1);
            N--;    // 마지막 운동기구를 제외
        }

        for (int i = 0; i < N/2; i++) { // 현재 상태에서 최소와 최대의 조합이 가장 적은 M
            long muscleLoss = muscleLossList.get(i) + muscleLossList.get(N - (i+1));
            minM = Math.max(minM, muscleLoss);
        }

        bw.write(String.valueOf(minM));
        bw.flush();
        bw.close();
    }
}