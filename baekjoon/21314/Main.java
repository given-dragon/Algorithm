import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String minkyumNum = br.readLine();

        String maxNum = calcMaxNum(minkyumNum);
        String minNum = calcMinNum(minkyumNum);

        bw.write(maxNum);
        bw.write('\n');
        bw.write(minNum);
        bw.flush();
        bw.close();
    }

    private static String calcMaxNum(String minkyumNum) {
        StringBuilder zeroSave = new StringBuilder();
        StringBuilder maxNum = new StringBuilder();
        int length = minkyumNum.length();

        // M과 K를 묶어서 크게 만드는게 핵심
        for (int i = 0; i < length; i++) {
            char current = minkyumNum.charAt(i);
            if (current == 'M') {
                zeroSave.append('0');
                continue;
            }
            if (current == 'K') {   // "MM...K 조합 추가
                maxNum.append('5');
                if (zeroSave.length() != 0) {
                    addZeroToNum(zeroSave, maxNum, false);
                    zeroSave = new StringBuilder();
                }
            }
        }
        if (zeroSave.length() != 0) {   // 남은 M 추가
            // 최댓값을 구하는 경우 마지막 자투리 M그룹을
            // 하나씩 분리하여 추가하는게 더 큼 -> MM: 10, M/M: 11
            maxNum.append("1".repeat(Math.max(0, zeroSave.length())));
        }
        return maxNum.toString();
    }

    private static String calcMinNum(String minkyumNum) {
        StringBuilder zeroSave = new StringBuilder();
        StringBuilder minNum = new StringBuilder();
        int length = minkyumNum.length();

        // M끼리 묶고, K는 분리해서 작게 만드는게 핵심
        for (int i = 0; i < length; i++) {
            char current = minkyumNum.charAt(i);
            if (current == 'M') {
                zeroSave.append('0');
                continue;
            }
            if (current == 'K') {
                if (zeroSave.length() != 0) {   // MM... 조합 추가 후 K추가
                    minNum.append('1');
                    addZeroToNum(zeroSave, minNum, true);
                    zeroSave = new StringBuilder();
                }
                minNum.append('5');
            }
        }

        if (zeroSave.length() != 0) {   // 남은 M 추가
            minNum.append('1');
            addZeroToNum(zeroSave, minNum, true);
        }
        return minNum.toString();
    }

    private static void addZeroToNum(StringBuilder zeroSave, StringBuilder num, boolean isGroupOfM) {
        if (isGroupOfM)
            zeroSave.deleteCharAt(zeroSave.length()-1); // M하나는 1이므로 0 제거하고 추가
        num.append(zeroSave);
    }

}