import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Map<Character, Character[]> binaryTree = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            char parentNode = st.nextToken().toCharArray()[0];
            char leftChildNode = st.nextToken().toCharArray()[0];
            char rightChildNode = st.nextToken().toCharArray()[0];

            binaryTree.put(parentNode, new Character[]{leftChildNode, rightChildNode});
        }

        sb.append(preorder(binaryTree, 'A')).append('\n');
        sb.append(inorder(binaryTree, 'A')).append('\n');
        sb.append(postorder(binaryTree, 'A')).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static String preorder(Map<Character, Character[]> binaryTree, char node) {
        if (node == '.')
            return "";

        String result="";
        Character[] childs = binaryTree.get(node);

        result += node;
        result += preorder(binaryTree, childs[0]);
        result += preorder(binaryTree, childs[1]);

        return result;
    }
    public static String inorder(Map<Character, Character[]> binaryTree, char node) {
        if (node == '.')
            return "";

        String result="";
        Character[] childs = binaryTree.get(node);

        result += inorder(binaryTree, childs[0]);
        result += node;
        result += inorder(binaryTree, childs[1]);

        return result;
    }
    public static String postorder(Map<Character, Character[]> binaryTree, char node) {
        if (node == '.')
            return "";

        String result="";
        Character[] childs = binaryTree.get(node);

        result += postorder(binaryTree, childs[0]);
        result += postorder(binaryTree, childs[1]);
        result += node;

        return result;
    }
}