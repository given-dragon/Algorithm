import java.io.*;
import java.util.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        int[][] lectureArr = new int[N][2];  //[lec][start][end]

        PriorityQueue<Lec> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.e));
        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Lec(
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken())));
        }

        int cnt = 1;
        Lec currLec = pq.poll();
        while(!pq.isEmpty()) {
            Lec nextLec = pq.poll();
            if (currLec.e <= nextLec.s) {
                currLec = nextLec;
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static class Lec {
        int s, e;
        public Lec(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}

