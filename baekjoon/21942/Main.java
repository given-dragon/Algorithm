import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());    // 대여장에 작성된 정보의 개수
        String L = st.nextToken();                  // 대여기간
        int F = Integer.parseInt(st.nextToken());   // 분당 벌금

        Map<String, String> map = new HashMap<>();    //<닉네임, [부품명, 대여시간]>
        Map<String, Long> fineMap = new TreeMap<>(Comparator.naturalOrder());    //<닉네임, 벌금>

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String strDateTime = st.nextToken() + " " + st.nextToken(); // 빌린||반납 시간
            String P = st.nextToken();  // 부품명
            String M = st.nextToken();  // 닉네임

            String key = M + " " + P;
            String strBorrowedDateTime;
            if ((strBorrowedDateTime = map.getOrDefault(key, null)) == null) {   // 부품 빌릴 경우
                map.put(key, strDateTime);
            }
            else {  // 부품 반납
                LocalDateTime expirationDateTime = getExpirationDateTime(strBorrowedDateTime, L);
                LocalDateTime returnDateTime = str2DateTime(strDateTime);
                Long fine = calcFine(expirationDateTime, returnDateTime, F);

                map.remove(key);
                if (fine > 0) {
                    fine += fineMap.getOrDefault(M, (long) 0);
                    fineMap.put(M, fine);
                }
            }
        }

        if (fineMap.isEmpty())
            sb.append(-1).append('\n');
        else
            for (Map.Entry<String, Long> entry : fineMap.entrySet())
                sb.append(entry.getKey()).append(' ').append(entry.getValue()).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static long calcFine(LocalDateTime expirationDateTime, LocalDateTime returnDateTime, int fine) {
        int between = (int) ChronoUnit.MINUTES.between(expirationDateTime, returnDateTime);
        return (long) Math.max(between, 0) * fine;
    }
    public static LocalDateTime getExpirationDateTime(String strBorrowedDateTime, String L) {
        LocalDateTime borrowedDateTime = str2DateTime(strBorrowedDateTime);
        StringTokenizer st = new StringTokenizer(L, "/:");
        int plusDay = Integer.parseInt(st.nextToken());
        int plusHour = Integer.parseInt(st.nextToken());
        int plusMint = Integer.parseInt(st.nextToken());

        return borrowedDateTime.plusDays(plusDay).plusHours(plusHour).plusMinutes(plusMint);
    }
    public static LocalDateTime str2DateTime(String strDateTime) {
        return LocalDateTime.parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}