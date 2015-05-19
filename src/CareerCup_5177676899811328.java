import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Igor_Sokolov on 5/18/2015.
 */
public class CareerCup_5177676899811328 {
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
    }

    private static Node prepareData() {
        Node one = new Node(1);

        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);

        one.left = two;
        one.right = three;

        two.left = four;
        two.right = five;

        three.left = six;
        three.right = seven;

        four.left = eight;
        four.right = nine;

        return one;
    }

    public static void main(String[] args) {
        Node root = prepareData();
        printZigzag(root);
    }

    private static class Entry {
        Node node;
        int level;

        public Entry(Node node) {
            this.node = node;
            this.level = 0;
        }

        public Entry(Node node, int level) {
            this.node = node;
            this.level = level;
        }

        public Entry subLeft() {
            return (this.node.left != null) ? new Entry(this.node.left, this.level + 1): null;
        }

        public Entry subRight() {
            return (this.node.right != null) ? new Entry(this.node.right, this.level + 1) : null;
        }
    }

    private static void printZigzag(Node root) {
        Deque<Entry>[] lists = new Deque[] {new LinkedList<>(), new LinkedList<>()};

        add(lists, new Entry(root));
        int level = 0;

        while (!isEmpty(lists)) {
            Entry e = enqueue(lists, level);

            if (level != e.level) {
                System.out.println();
            }
            System.out.print(e.node + " ");

            Entry subLeft = e.subLeft();
            if (subLeft != null) {
                add(lists, subLeft);
            }

            Entry subRight = e.subRight();
            if (subRight != null) {
                add(lists, subRight);
            }

            level = e.level;
        }
    }

    private static Entry enqueue(Deque<Entry>[] lists, int level) {
        int idx = level % 2;
        if (lists[idx].isEmpty()) {
            return lists[(level + 1) % 2].removeFirst();
        } else {
            return lists[idx].removeFirst();
        }
    }

    private static boolean isEmpty(Deque<Entry>[] lists) {
        return lists[0].isEmpty() && lists[1].isEmpty();
    }

    private static void add(Deque<Entry>[] lists, Entry entry) {
        if (entry.level % 2 == 0) {
            lists[0].addFirst(entry);
        } else {
            lists[1].addLast(entry);
        }
    }
}
