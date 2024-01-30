import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int switchCount;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		switchCount = Integer.parseInt(br.readLine());
		boolean[] switchArr = new boolean[switchCount+1];
		String[] switchInfo = br.readLine().split(" ");
		
		for (int i=1; i<=switchCount; i++) {
			switchArr[i] = switchInfo[i-1].charAt(0) == '0' ? false : true;
		}
		
		
		int studentCount = Integer.parseInt(br.readLine());
		Student[] studentArr = new Student[studentCount];
		for (int i=0; i<studentCount; i++) {
			String[] split = br.readLine().split(" ");
			char s = split[0].charAt(0);
			int num = Integer.parseInt(split[1]);
			studentArr[i] = new Student(s, num);
		}
		
		for (Student s : studentArr) {
			if (s.s == '1') {
				man(switchArr, s.num);
				continue;
			}
			woman(switchArr, s.num);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=switchCount; i++) {
			sb.append(switchArr[i] ? '1' : '0').append(' ');
			if (i % 20 == 0) {
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void man(boolean[] switchArr, int num) {
		for (int i=num; i<= switchCount; i+=num) {
			switchArr[i] = !switchArr[i];
		}
	}
	
	public static void woman(boolean[] switchArr, int num) {
		switchArr[num] = !switchArr[num];
		
		for (int i=1; i<=switchCount; i++) {
			if ( (num-i<1) || (switchCount<(num+i)) ) {
				break;
			}
			if (switchArr[num-i] != switchArr[num+i]) {
				break;
			}
			
			switchArr[num-i] = !switchArr[num-i];
			switchArr[num+i] = !switchArr[num+i];
		}
	}
	
	static class Student {
		char s;
		int num;
		public Student(char s, int num) {
			this.s = s;
			this.num = num;
		}
	}
}

