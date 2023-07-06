import java.io.*;
import java.util.*;

public class Main {


    public static class Queue {

//        ArrayList<Integer> queue = new ArrayList<>();
        LinkedList<Integer> list;
        public Queue() {
             list = new LinkedList<>();
        }
        public void push(int i) {
            list.add(i);
        }
        public int pop() {
            if (size() == 0)
                return  -1;

            int num = list.getFirst();
            list.removeFirst();
            return num;
        }
        public int size() {
            return list.size();
        }
        public int empty() {
            return (size()==0) ? 1 : 0;
        }
        public int front() {
            return (size() > 0) ? list.getFirst() : -1;
        }
        public int back() {
            return (size() > 0) ? list.getLast() : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Queue queue = new Queue();

        while (N > 0) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");

            String command = st.nextToken();

            switch (command) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    queue.push(num);
                    break;
                case "pop":
                    sb.append(queue.pop()).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    sb.append(queue.empty()).append("\n");
                    break;
                case "front":
                    sb.append(queue.front()).append("\n");
                    break;
                case "back":
                    sb.append(queue.back()).append("\n");
                    break;
            }

            N--;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}