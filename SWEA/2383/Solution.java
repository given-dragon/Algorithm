import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int N, personCount, minTime;
	static int[] result;
	static List<Person> personList;
	static List<Stair> stairList;

	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());

			personList = new ArrayList<>();
			stairList = new ArrayList<>();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {

				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {

					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						personList.add(new Person(i, j));
						continue;
					}

					if (map[i][j] >= 2) {
						stairList.add(new Stair(i, j, map[i][j]));
					}
				}
			}

			minTime = Integer.MAX_VALUE;
			personCount = personList.size();
			result = new int[personCount];

			recur(0);
			sb.append('#').append(tc).append(' ').append(minTime).append('\n');
		}

		bw.append(sb);
		bw.flush();
	}

	public static void recur(int idx) {
		if (idx == personCount) {
			int time = simulation();
			minTime = Math.min(minTime, time);
			return;
		}

		result[idx] = 0;
		recur(idx+1);

		result[idx] = 1;
		recur(idx+1);
	}

	private static int simulation() {
		List<Person> copiedPersonList = copyPersonList();
		for (int i = 0; i < personCount; i++) {
			Person p = copiedPersonList.get(i);
			p.stairChoice = result[i];

			Coord stair = stairList.get(p.stairChoice);
			p.remainingTime = Math.abs(p.r - stair.r) + Math.abs(p.c - stair.c);
		}

		copiedPersonList.sort((p1, p2) -> -1*Integer.compare(p1.remainingTime, p2.remainingTime));

		int outCount = 0, time = 0;
		while (++time > 0) {
			for (Stair stair : stairList) {
				for (int i = stair.list.size()-1; i >= 0; i--) {

					Person p = stair.list.get(i);
					if (--p.remainingTime == 0) {
						stair.list.remove(i);
						outCount++;
					}
				}
			}

			if (outCount == personCount) {
				break;
			}

			for (int i = copiedPersonList.size()-1; i >= 0; i--) {

				Person p = copiedPersonList.get(i);
				if (--p.remainingTime <= 0) {

					Stair stair = stairList.get(p.stairChoice);
					if (stair.list.size() >= 3) {
						continue;
					}

					p.remainingTime = (stair.len + (p.remainingTime == 0 ? 1 : 0));
					stair.list.add(p);
					copiedPersonList.remove(i);
				}
			}
		}
		return time;
	}

	private static List<Person> copyPersonList() {
		List<Person> tempPersonList = new ArrayList<>();
		for (Person p : personList) {
			Person tempPerson = new Person(p.r, p.c);
			tempPerson.remainingTime = p.remainingTime;
			tempPersonList.add(tempPerson);
		}
		return tempPersonList;
	}

	static class Stair extends Coord {
		int len;
		List<Person> list;
		public Stair(int r, int c, int len) {
			super(r, c);
			this.len = len;
			list = new ArrayList<>();
		}
	}
	static class Person extends Coord{
		int stairChoice, remainingTime;
		public Person(int r, int c) {
			super(r, c);
		}
	}
	static class Coord {
		int r, c;
		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
