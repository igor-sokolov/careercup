import java.util.*;

/**
 * Created by Igor_Sokolov on 6/16/2015.
 */
public class CareerCup_5991240778645504 {
    private static final int K = 1000;

    private static class UF {
        int[] parent;
        int[] rank;

        public UF(int size) {
            this.parent = new int[size];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            this.rank = new int[size];
            Arrays.fill(rank, 1);
        }

        public void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);

            if (rank[pi] == rank[pj]) {
                parent[pi] = pj;
                rank[pj]++;
            } else if (rank[pi] > rank[pj]) {
                parent[pj] = pi;
            } else {
                parent[pi] = pj;
            }
        }

        public int find(int j) {
            int p = j;
            while (parent[p] != p) {
                p = parent[p];
            }

            while (parent[j] != p) {
                int x = parent[j];
                parent[j] = p;
                j = x;
            }

            return p;
        }
    }
    public static void main(String[] args) {
        LinkedList<Integer>[] lists = prepareData();

        List<List<Integer>> result = findIntersections(lists);

        printResult(result);
    }

    private static void printResult(List<List<Integer>> result) {
        for (List<Integer> list: result) {
            StringBuilder sb = new StringBuilder();
            for (int i: list) {
                sb.append(i).append(' ');
            }
            System.out.println(sb);
        }
    }

    private static LinkedList<Integer>[] prepareData() {
        LinkedList<Integer>[] lists = new LinkedList[5];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new LinkedList<>();
        }

        lists[0].addAll(Arrays.asList(1, 2, 3, 4, 5));
        lists[1].addAll(Arrays.asList(6, 7, 8, 4, 5));
        lists[2].addAll(Arrays.asList(8, 9, 10, 11, 12));
        lists[3].addAll(Arrays.asList(13, 14, 15, 12));
        lists[4].addAll(Arrays.asList(16, 17, 18 ));
        return lists;
    }

    private static List<List<Integer>> findIntersections(LinkedList<Integer>[] input) {
        UF uf = new UF(K);
        final int N = input.length;

        for (int i = 0; i < N; i++) {
            int head = input[i].getFirst();
            for (int v : input[i]) {
                uf.union(head, v);
            }
        }

        boolean[] visited = new boolean[N];
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int x = input[i].getFirst();

                List<Integer> list = new ArrayList<>();
                list.add(x);

                for (int j = i + 1; j < N; j++) {
                    int y = input[j].getFirst();
                    if (uf.find(x) == uf.find(y)) {
                        visited[j] = true;
                        list.add(y);
                    }
                }

                result.add(list);
            }
        }

        return result;
    }
}
