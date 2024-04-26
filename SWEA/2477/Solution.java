import java.io.*;
import java.util.*;

/**
 * [풀이]
 *
 * 현재시간을 ++
 * 접수 & 대기 배열을 순회하며 빈 배열이 있고,
 * peek()의 시간이 현재시간보다 늦다면 큐에서 poll하여 추가
 *
 * 고객이 오는대로 큐에서 대기 - receptionQueue
 *  우선순위큐 - 고객번호 낮은 순
 *
 * 접수 창구 배열 => [창구번호] => 고객이 끝나는 시간 == 현재시간 + 새로운 고객 시간
 *  현재시간 >= [창구번호] --> 새로운 고객 진입 가능
 *
 * 끝난 고객은 정비 창구 대기 큐로 이동 repairQueue
 *  일반 큐
 *  다만, 접수를 동시에 완료하는 고객이 있을경우 접수창구 낮은 순
 *      한 사이클마다 접수가 끝난 고객들을 리스트에 추가 => 정렬 후 정비큐에 추가
 */
class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, M, K, A, B;
    static int[] receptionTimeArr, repairTimeArr;
    static Customer[] receptionDeskArr, repairDeskArr;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 접수 수
            M = Integer.parseInt(st.nextToken());   // 정비 수
            K = Integer.parseInt(st.nextToken());   // 고객 수
            A = Integer.parseInt(st.nextToken());   // 목표 접수 번호
            B = Integer.parseInt(st.nextToken());   // 목표 정비 번호

            receptionDeskArr = new Customer[N + 1];
            receptionTimeArr = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                receptionTimeArr[i] = Integer.parseInt(st.nextToken());
            }

            repairDeskArr = new Customer[M + 1];
            repairTimeArr = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                repairTimeArr[i] = Integer.parseInt(st.nextToken());
            }

            PriorityQueue<Customer> receptionQue = new PriorityQueue<>(Comparator.comparingInt(c -> c.no));
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                receptionQue.add(new Customer(i, Integer.parseInt(st.nextToken())));
            }

            Queue<Customer> repairQue = new ArrayDeque<>();

            int sum = 0;
            int currTime = 0;
            int endCustomer = 0;
            while (endCustomer < K) {

                // 접수 제거
                List<Customer> repairWaitingList = new ArrayList<>();
                for (int i = 1; i <= N; i++) {
                    if (receptionDeskArr[i] == null || (receptionDeskArr[i].endTime > currTime)) {
                        continue;
                    }
                    repairWaitingList.add(receptionDeskArr[i]);
                    receptionDeskArr[i] = null;
                }
                // 접수 추가
                for (int i = 1; i <= N; i++) {
                    if (receptionQue.isEmpty() || receptionQue.peek().arrivTime > currTime) {
                        break;
                    }
                    if (receptionDeskArr[i] != null) {
                        continue;
                    }

                    receptionDeskArr[i] = receptionQue.poll();
                    receptionDeskArr[i].receptionNo = i;
                    receptionDeskArr[i].endTime = currTime + receptionTimeArr[i];
                }

                repairWaitingList.sort(Comparator.comparingInt(c -> c.receptionNo));
                repairQue.addAll(repairWaitingList);

                // 정비 제거
                for (int i = 1; i <= M; i++) {
                    if (repairDeskArr[i] == null || (repairDeskArr[i].endTime > currTime)) {
                        continue;
                    }
                    repairDeskArr[i] = null;
                }

                // 정비 추가
                for (int i = 1; i <= M; i++) {
                    if (repairQue.isEmpty()) {
                        break;
                    }
                    if (repairDeskArr[i] != null) {
                        continue;
                    }
                    repairDeskArr[i] = repairQue.poll();
                    repairDeskArr[i].repairNo = i;
                    repairDeskArr[i].endTime = currTime + repairTimeArr[i];

                    if (repairDeskArr[i].receptionNo == A && repairDeskArr[i].repairNo == B) {
                        sum += repairDeskArr[i].no;
                    }

                    endCustomer++;
                }

                currTime++;
            }
            sb.append('#').append(tc).append(' ').append(sum==0?-1:sum).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    static class Customer {
        int no, arrivTime, endTime, receptionNo, repairNo;
        public Customer(int no, int arrivTime) {
            this.no = no;
            this.arrivTime = arrivTime;
        }
    }
}