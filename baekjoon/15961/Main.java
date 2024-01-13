import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]); // 벨트 위 접시 수
        int d = Integer.parseInt(split[1]); // 초밥의 가짓수
        int k = Integer.parseInt(split[2]); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(split[3]); // 쿠폰 번호

        int[] sushiBelt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sushiBelt[i] = Integer.parseInt(br.readLine());
        }

        int[] choiceArr = new int[d+1];
        choiceArr[c] = 1;

        int typeCount = 1;
        for (int i = 1; i <= k; i++) {
            typeCount = choiceSushi(choiceArr, sushiBelt[i], typeCount);
        }
        int maxTypeCount = typeCount;

        // 벨트를 한바퀴 돌며 최대 스시 개수 검색
        int fc = k; // front cursor
        int rc = 1; // rear cursor
        fc = nextCursor(fc, N);
        while (fc != k){
            typeCount = choiceSushi(choiceArr, sushiBelt[fc], typeCount);
            typeCount = removeSushi(choiceArr, sushiBelt[rc], typeCount);
            fc = nextCursor(fc, N);
            rc = nextCursor(rc, N);

            maxTypeCount = Math.max(maxTypeCount, typeCount);
        }
        System.out.println(maxTypeCount);
    }

    public static int removeSushi(int[] choiceArr, int sushi, int typeCount) {
        if (choiceArr[sushi] == 1)
            typeCount--;
        choiceArr[sushi]--;
        return typeCount;
    }
    public static int choiceSushi(int[] choiceArr, int sushi, int typeCount) {
        // 스시를 선택하고, 선택하지 않았던 종류면 가짓수 + 1
        if (choiceArr[sushi] == 0)
            typeCount++;
        choiceArr[sushi]++;
        return typeCount;
    }
    public static int nextCursor(int cursor, int N) {
        return cursor % N + 1;
    }
}
