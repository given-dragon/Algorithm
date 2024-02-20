import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, minDist = Integer.MAX_VALUE;
	static List<Coord> houseList = new ArrayList<>();
	static List<Coord> chickenList = new ArrayList<>();
	static int[] selectedChicken;
	static int[][] chickenDistArr;
	
	public static void main(String[] args) throws Exception {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			String line = br.readLine();
			
			for (int j=0; j<N; j++) {
				if (line.charAt(j<<1) == '1') {
					houseList.add(new Coord(i, j));
					continue;
				}
				if (line.charAt(j<<1) == '2') {
					chickenList.add(new Coord(i, j));
				}
			}
		}
		
		// 치킨거리를 저장
		chickenDistArr = new int[houseList.size()][chickenList.size()];
		for (int i = houseList.size()-1; i>=0; i--) {
			for (int j = chickenList.size()-1; j>=0; j--) {
				chickenDistArr[i][j] = getChickenDist(houseList.get(i), chickenList.get(j));
			}	
		}
		
		selectedChicken = new int[M];
		searchChickenComb(0, 0);
		
		System.out.println(minDist);
	}
	
	public static void searchChickenComb(int cnt, int cIdx) {
		if (cnt == M) {
			int cityChickenDist = getCityChickenDist();
			minDist = Math.min(minDist, cityChickenDist);
			return;
		}
		
		for (int i=cIdx; i<chickenList.size(); i++) {
			selectedChicken[cnt] = i;
			searchChickenComb(cnt+1, i+1);
		}
	}
	
	public static int getCityChickenDist() {
		int totalDist = 0;
		for (int i = houseList.size()-1; i>=0; i--) {
			
			int chickenDist = Integer.MAX_VALUE;
			for (int j : selectedChicken) {
				int nChickenDist = chickenDistArr[i][j];
				chickenDist = Math.min(chickenDist, nChickenDist);
			}
			
			totalDist += chickenDist;
			if (totalDist > minDist) {
				return Integer.MAX_VALUE;
			}
		}
		
		return totalDist;
	}
	
	public static int getChickenDist(Coord house, Coord chicken) {
		return Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
	}
	
	static class Coord {
		int r, c;
		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
