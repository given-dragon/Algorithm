import java.io.*;
import java.util.*;

/**
 * 원자들은 1초에 1만큼 움직임
 *  만약 아래의 두 원자가 있다면
 *  (0, 0)&->    <-(0, 1)
 *  0.5초 뒤에 충돌하여 소멸
 *  @@ 배열의 크기를 2배 늘리고, 움직임을 0.5마다 계산
 *
 *  좌표의 범위는 -1000 ~ 1000 => 0 ~ 2000 => 0 ~ 4000
 *
 *  원자가 충돌하는 최악의 상황은 양 끝에 있을 때.
 *  따라서 반복은 4000번만 해주면 됨.
 *
 *  충돌 판별
 *      원자 하나를 이동할 때 마다 위치 계산 row * MAX_LEN + col
 *      map -> <Atom, int> == <원자, 에너지>
 *      map에 해당 위치에 원자가 없다면 추가 put(num, atom.k)
 *      map에 해당 위치에 원자가 있다면 에너지 k 증가
 *
 * 점수 계산
 *  map의 요소를 하나씩 꺼내 에너지가 k 초과 => sum
 *  에너지가 k인 요소들은 다음 이동을 위해 list에 push
 *
 * 시간 최적화
 *  1. 원자 개수가 1개 이하면 종료
 *  2. 원자의 row & col이 0 ~ 4000을 벗어나면 버리기
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int MAX_LEN = 4000;

    static int N;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            List<Atom> atomList = new ArrayList<>(1000);    // 움직여야할 원자 맵
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int c = (Integer.parseInt(st.nextToken()) + 1000) << 1;
                int r = (Integer.parseInt(st.nextToken()) + 1000) << 1;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atomList.add(new Atom(r, c, d, k));
            }

            Map<Atom, Integer> movedResultMap = new HashMap<>(1000);
            int energySum = 0;
            for (int cnt = 0; cnt < 4000; cnt++) {
                if (atomList.size() < 2) {
                    break;
                }

                movedResultMap.clear();
                for (Atom atom : atomList) {
                    atom.move();
                    if (isOutOfBound(atom.r, atom.c)) {
                        continue;
                    }

                    if (movedResultMap.containsKey(atom)) {
                        int energy = movedResultMap.get(atom);
                        movedResultMap.replace(atom, energy + atom.k);
                    }
                    else {
                        movedResultMap.put(atom, atom.k);
                    }
                }

                atomList.clear();
                for (Map.Entry<Atom, Integer> set : movedResultMap.entrySet()) {
                    if (set.getValue() > set.getKey().k) {
                        energySum += set.getValue();
                        continue;
                    }
                    atomList.add(set.getKey());
                }
            }
            sb.append('#').append(tc).append(' ').append(energySum).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || MAX_LEN <r || c<0 || MAX_LEN <c;
    }

    static class Atom {
        int r, c, d, k;
        public Atom(int r, int c, int d, int k) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.k = k;
        }

        public void move() {
            this.r += dr[this.d];
            this.c += dc[this.d];
        }

        @Override
        public boolean equals(Object o) {
            Atom atom = (Atom) o;
            return this.r == atom.r && this.c == atom.c;
        }
        @Override
        public int hashCode() {
            return this.r * MAX_LEN + c;
        }
    }
}