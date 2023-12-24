import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 크레인 대수
        int[] craneCapacityArr = strToArr(br.readLine());
        int M = Integer.parseInt(br.readLine());    // 박스 수
        int[] boxWeightArr = strToArr(br.readLine());

        Arrays.sort(craneCapacityArr);
        Arrays.sort(boxWeightArr);

        if (craneCapacityArr[N-1] < boxWeightArr[M-1]) {
            System.out.println(-1);
            return;
        }

        // 초기 화물 할당 -> 적은 크레인부터 할당
        int[] craneQuota = new int[N];
        int boxIdx = 0;
        for (int i = 0; i < N; i++) {
            while (boxWeightArr[boxIdx] <= craneCapacityArr[i]) {
                craneQuota[i]++;
                boxIdx++;
                if (boxIdx == M) {
                    break;
                }
            }
            if (boxIdx == M) {
                break;
            }
        }

        int time = 0;

        // 화물 분배 -> 큰 크레인부터 시작
        while (M > 0) {
            int waitingCraneCount = 0; // 작업을 하기 위해 대기중인 크레인 수
            for (int i = N-1; i >= 0; i--) {
                int quota = craneQuota[i];
                waitingCraneCount++;
                if (quota == 0) {   // 할당된 화물이 없을 -> 다른 크레인걸 도와주기 위해 대기
                    continue;
                }

                if (quota >= waitingCraneCount) {   // 처리할 화물이 대기중인 크레인 이상일 때
                    M-=waitingCraneCount;
                    craneQuota[i]-=waitingCraneCount;
                    waitingCraneCount = 0;

                }
                else { //   (quota < waitingCraneCount) -> 대기중인 크레인에게 모두 할당 못할 때
                    M-=craneQuota[i];   // 전체 박스에서 할당량만큼 제거
                    waitingCraneCount -= craneQuota[i]; // 대기중인 크레인에서 처리해야할 화물만큼 제거
                    craneQuota[i] = 0;  // 크레인에 배정된 화물을 모두 처리했음
                }
            }
            time++;
        }

        System.out.println(time);
    }
    static int[] strToArr(String str) {
        String[] split = str.split(" ");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return arr;
    }
}