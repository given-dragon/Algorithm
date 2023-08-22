import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int M = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minQ = new PriorityQueue<>(Comparator.naturalOrder());
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
            sb.append(M/2+1).append('\n');

            int totalSeqLine = M / 10 + (M % 10 > 0 ? 1 : 0);
            int spaceCounter = 0;
            int mid;

            for (int j = 0; j < totalSeqLine; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int numCountInLine = st.countTokens();

                for (int k = 1; k <= numCountInLine; k++) {
                    if (maxQ.isEmpty() && minQ.isEmpty()) {
                        mid = Integer.parseInt(st.nextToken());
                        maxQ.add(mid);
                    } else if (maxQ.size() > minQ.size()) {
                        int currentNum = Integer.parseInt(st.nextToken());
                        if (maxQ.peek() > currentNum) {
                            minQ.add(maxQ.poll());
                            maxQ.add(currentNum);
                        } else {
                            minQ.add(currentNum);
                        }
                    } else if (maxQ.size() == minQ.size()) {
                        int currentNum = Integer.parseInt(st.nextToken());
                        if (maxQ.peek() < currentNum) {
                            minQ.add(currentNum);
                            maxQ.add(minQ.poll());
                        } else {
                            maxQ.add(currentNum);
                        }
                    }

                    if ((k & 1) == 1) {
                        sb.append(maxQ.peek()).append(' ');
                        spaceCounter++;
                    }
                }
                if (spaceCounter % 10 == 0) {
                    sb.append('\n');
                    spaceCounter=0;
                }
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}


//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
//
//        int T = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < T; i++) {
//            int M = Integer.parseInt(br.readLine());
//            ArrayList<Integer> arrayList = new ArrayList<>();
//            sb.append(M/2+1).append('\n');
//
//            int totalSeqLine = M / 10 + (M % 10 > 0 ? 1 : 0);
//            int spaceCounter = 0;
//
//            for (int j = 0; j < totalSeqLine; j++) {
//                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//                int numCountInLine = st.countTokens();
//
//                for (int k = 1; k <= numCountInLine; k++) {
//                    arrayList.add(Integer.parseInt(st.nextToken()));
//
//                    if ((k & 1) == 1) {
//                        arrayList.sort(Comparator.naturalOrder());
//                        sb.append(peekCenter(arrayList)).append(' ');
//                        spaceCounter++;
//                    }
//                }
//                if (spaceCounter % 10 == 0) {
//                    sb.append('\n');
//                    spaceCounter=0;
//                }
//            }
//            sb.append('\n');
//        }
//
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();
//    }
//    public static int peekCenter(ArrayList<Integer> arrayList) {
//        int size = arrayList.size();
//        Object[] array = arrayList.toArray();
//        return (int) array[size / 2];
//    }
//}
