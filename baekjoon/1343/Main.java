import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String[] boards = str.split("\\.");

        for (String board : boards) {
            StringBuilder tempSB = new StringBuilder();
            int len = board.length();

            if (len == 0)
                continue;

            while (len > 0) {
                if ((len % 4) == 0) {
                    sb.append("AAAA".repeat(len / 4));
                    break;
                }
                len -= 2;
                tempSB.append("BB");
            }

            if (len < 0) {
                sb = new StringBuilder();
                sb.append(-1);
                break;
            } else {
                sb.append(tempSB);
            }
        }
        bw.write((sb.toString().equals("-1") ? sb : makeString(str, sb)).toString());
        bw.flush();
        bw.close();
    }

    private static StringBuilder makeString(String str, StringBuilder sb) {
        StringBuilder result = new StringBuilder();
        int idx = 0;
        for (char c : str.toCharArray()) {
            if (c=='X')
                result.append(sb.charAt(idx++));
            else
                result.append('.');
        }
        return result;
    }
}