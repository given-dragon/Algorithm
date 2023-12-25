import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long peopleCountSum = 0;
        Village[] villages = new Village[N];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            villages[i] = new Village(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            peopleCountSum += villages[i].peopleCount;
        }
        Arrays.sort(villages);

        double middlePoint = peopleCountSum / 2.0;

        long leftSidePeopleCountSum = 0;
        for (Village village : villages) {
            leftSidePeopleCountSum += village.peopleCount;
            if ((double) leftSidePeopleCountSum >= middlePoint) {
                System.out.println(village.pos);
                return;
            }
        }
    }
    static class Village implements Comparable<Village> {
        int pos;
        int peopleCount;

        public Village(int pos, int peopleCount) {
            this.pos = pos;
            this.peopleCount = peopleCount;
        }

        @Override
        public int compareTo(Village v) {
            return Long.compare(this.pos, v.pos);
        }
    }
}