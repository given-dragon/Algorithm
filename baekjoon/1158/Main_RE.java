import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);
		
		ArrayList<Integer> list = new ArrayList<>(N);
		for (int i=1; i<=N; i++) {
			list.add(i);
		}
		
		sb.append('<');
		int killIdx = K-1;
		for (int i=0; i<N-1; i++) {
			sb.append(list.remove(killIdx)).append(", ");
			killIdx = (killIdx + (K-1)) % list.size();
		}
		
		sb.append(list.remove(0)).append(", ");
		sb.delete(sb.length()-2, sb.length());
		sb.append('>');
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}

