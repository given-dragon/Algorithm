import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static Map<String, Folder> folderMap = new HashMap<>();
    static Map<String, Integer> fileMap = new HashMap<>();
    static int[] adjArr, indegree;
    static Folder[] folderArr;
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 폴더 수
        M = Integer.parseInt(st.nextToken());   // 파일 수

        int infoCnt = N+M;
        N += 1; // add main folder
        folderArr = new Folder[N+1];
        adjArr = new int[N+1];    // [child no] = parent no
        indegree = new int[N+1];

        init(infoCnt);

        countingFiles();

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            String[] folderArr = br.readLine().split("/");
            String targetFolder = folderArr[folderArr.length - 1];

            Folder folder = folderMap.get(targetFolder);
            sb.append(folder.fileSet.size()).append(' ').append(folder.fileCnt).append('\n');
        }
        bw.append(sb);
        bw.flush();
    }

    private static void countingFiles() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int targetNo = queue.poll();
            int parentNo = adjArr[targetNo];
            if (parentNo == 0) {
                break;
            }

            folderArr[parentNo].fileCnt += folderArr[targetNo].fileCnt;
            folderArr[parentNo].fileSet.addAll(folderArr[targetNo].fileSet);
            if (--indegree[parentNo] == 0) {
                queue.add(parentNo);
            }
        }
    }

    private static void init(int infoCnt) throws IOException {
        int folderIdx = 1, fileIdx = 1;
        while (infoCnt-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            String parent = st.nextToken();
            String child = st.nextToken();
            boolean isFolder = st.nextToken().charAt(0) == '1';

            if (!folderMap.containsKey(parent)) {
                folderArr[folderIdx] = new Folder(folderIdx, 0);
                folderMap.put(parent, folderArr[folderIdx]);
                folderIdx++;
            }
            if (isFolder && !folderMap.containsKey(child)) {
                folderArr[folderIdx] = new Folder(folderIdx, 0);
                folderMap.put(child, folderArr[folderIdx]);
                folderIdx++;
            }

            if (!isFolder) {
                if (!fileMap.containsKey(child)) {
                    fileMap.put(child, fileIdx);
                    fileIdx++;
                }
                Folder parentFolder = folderMap.get(parent);
                parentFolder.fileCnt++;
                parentFolder.fileSet.add(fileMap.get(child));
                continue;
            }

            int parentNo = folderMap.get(parent).no;
            int childNo = folderMap.get(child).no;
            adjArr[childNo] = parentNo;
            indegree[parentNo]++;
        }
    }

    static class Folder {
        int no, fileCnt;
        Set<Integer> fileSet;
        public Folder(int no, int fileCnt) {
            this.no = no;
            this.fileCnt = fileCnt;
            this.fileSet = new HashSet<>();
        }
    }
}