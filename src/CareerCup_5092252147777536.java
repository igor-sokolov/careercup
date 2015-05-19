import java.util.BitSet;

/**
 * Created by Igor_Sokolov on 5/18/2015.
 */
public class CareerCup_5092252147777536 {
    private static class Node {
        int value;

        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        Node setLeft(Node left) {
            this.left = left;
            return this;
        }

        Node setRight(Node right) {
            this.right = right;
            return this;
        }
    }

    private static Node prepareData() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);
        Node node6 = new Node(3);
        Node node7 = new Node(4);

        node1.setLeft(node2).setRight(node3);
        node2.setLeft(node4).setRight(node5);
        node3.setLeft(node7).setRight(node6);

        return node1;
    }

    public static void main(String[] args) {
        Node root = prepareData();
        System.out.println(checkFoldable(root));
    }

    private static boolean checkFoldable(Node root) {
        if (root == null) {
            return true;
        }

        return checkFoldable(root.left, root.right);
    }

    private static boolean checkFoldable(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 != null ^ root2 != null || root1.value != root2.value) {
            return false;
        }

        return checkFoldable(root1.left, root2.right) && checkFoldable(root1.right, root2.left);
    }

}
