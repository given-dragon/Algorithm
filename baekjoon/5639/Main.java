import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String nodeStr = br.readLine();
        Node root = new Node(Integer.parseInt(nodeStr));

        while (true) {
            nodeStr = br.readLine();
            if (nodeStr == null || nodeStr.isEmpty())
                break;

            insert(root, new Node(Integer.parseInt(nodeStr)));
        }
        postorder(root, sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class Node {
        int num;
        Node left, right;
        public Node(int num) {
            this.num = num;
            left = right = null;
        }
    }

    public static void insert(Node root, Node node) {
        if (node.num < root.num) {
            if (root.left == null) {
                root.left = node;
                return;
            }
            insert(root.left, node);
        } else {    // if (node.num > root.num)
            if (root.right == null) {
                root.right = node;
                return;
            }
            insert(root.right, node);
        }
    }

    public static void postorder(Node node, StringBuilder sb) {
        if (node == null)
            return;

        postorder(node.left, sb);
        postorder(node.right, sb);
        sb.append(node.num).append('\n');
    }
}