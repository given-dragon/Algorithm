import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int N, K, minArea = Integer.MAX_VALUE;
    static List<Spot>[] colorList;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 점 개수
        K = Integer.parseInt(st.nextToken());  // 색 개수

        colorList = new ArrayList[K+1];
        for (int i=1; i<=K; i++) {
            colorList[i] = new ArrayList<>();
        }
        
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            colorList[c].add(new Spot(x, y));
        }

        recur(1, 1000, -1000, 1000, -1000);
        System.out.println(minArea);
        
    }

    public static void recur(int color, int leftX, int rightX, int leftY, int rightY) {
        if (color > K) {
            // replace min
            minArea = Math.min(minArea, (rightX-leftX)*(rightY-leftY));
            return;
        }

        for (Spot spot : colorList[color]) {
            int nLeftX = Math.min(leftX, spot.x);
            int nRightX = Math.max(rightX, spot.x);
            int nLeftY = Math.min(leftY, spot.y);
            int nRightY = Math.max(rightY, spot.y);

            if (minArea > (nRightX-nLeftX)*(nRightY-nLeftY)) {
                recur(color+1, nLeftX, nRightX, nLeftY, nRightY);
            }
            
        }
    }

    static class Spot {
        int x, y;
        public Spot (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

