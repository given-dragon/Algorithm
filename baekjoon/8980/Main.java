import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int N = Integer.parseInt(split[0]); // 마을 수
        int C = Integer.parseInt(split[1]); // 트럭 용량

        int M = Integer.parseInt(br.readLine());    // 보내는 박스 정보의 개수

        Box[] boxes = new Box[M];
        for (int i = 0; i < M; i++) {
            boxes[i] = makeBoxInfo(br, i);
        }
        Arrays.sort(boxes);

        int dropedBoxCount = 0;

        int[] village = new int[N];
        for (int i = 0; i < M; i++) {
            Box box = boxes[i];

            int villageQuantity = 0;
            for (int j = box.from; j < box.to; j++) {   // 지나가는 경로에 트럭 용량 확인
                villageQuantity = Math.max(villageQuantity, village[j]);
            }
            if (villageQuantity == C)
                continue;

            // 경로에 트럭 잔여 용량 추가
            box.quantity = Math.min(box.quantity, C - villageQuantity);
            for (int j = box.from; j < box.to; j++) {
                village[j] += box.quantity;
            }
            dropedBoxCount += box.quantity;
        }
        System.out.println(dropedBoxCount);
    }
    private static Box makeBoxInfo(BufferedReader br, int i) throws IOException {
        String[] split = br.readLine().split(" ");
        int from = Integer.parseInt(split[0]) - 1;
        int to = Integer.parseInt(split[1]) - 1;
        int quantity = Integer.parseInt(split[2]);
        return new Box(from, to, quantity);
    }

    static class Box implements Comparable<Box>{
        int from;
        int to;
        int quantity;

        public Box(int from, int to, int quantity) {
            this.from = from;
            this.to = to;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(Box b) {
            if (this.to == b.to)
                return (this.from < b.from) ? 1 : -1;
            return Integer.compare(this.to, b.to);
        }
    }

}