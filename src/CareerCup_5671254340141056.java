import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sis on 5/26/15.
 */
public class CareerCup_5671254340141056 {
    private static class Point {
        int x, y;

        public Point(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + (x + 1) + "," + (y + 1) +')';
        }
    }

    public static void main(String[] args) {
        int[][] m = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}
        };

        List<List<Point>> res = ufApproach(m);

        for (int i = 0; i < res.size(); i++) {
            System.out.printf("#%d {", i);
            res.get(i).forEach(System.out::print);
            System.out.println("}");
        }
    }

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

    private static List<List<Point>> ufApproach(int[][] m) {
        if (m.length == 0 || m[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int H = m.length;
        int W = m[0].length;

        int[][] offsets = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        UF uf = new UF(W * H);

        for (int ys = 0; ys < H; ys++) {
            for (int xs = 0; xs < W; xs++) {
                if (m[ys][xs] == 1) {
                    int value1 = ys * W + xs;

                    for (int i = 0; i < offsets.length; i++) {
                        int x = xs + offsets[i][0];
                        int y = ys + offsets[i][1];

                        if (x >= 0 && x < W && y >= 0 && m[y][x] == 1) {
                            int value2 = y * W + x;
                            uf.union(value1, value2);
                        }
                    }
                }
            }
        }

        HashMap<Integer, List<Point>> subres = new HashMap<>();
        for (int i = 0; i < uf.parent.length; i++) {
            int p = uf.find(i);
            if (p != i || uf.rank[i] > 1) {
                List<Point> points = subres.get(p);
                if (points == null) {
                    points = new ArrayList<>();
                }

                points.add(new Point(i / W, i % W));
                subres.put(p, points);
            }
        }

        return subres.values().stream().collect(Collectors.toList());
    }

    private static List<List<Point>> getAdjacentNodes(int[][] m) {
        if (m.length == 0 || m[0].length == 0) {
            throw new IllegalArgumentException();
        }

        List<List<Point>> res = new ArrayList<>();
        int H = m.length;
        int W = m[0].length;
        boolean[][] visit = new boolean[H][W];

        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        Queue<Point> q = new LinkedList<>();
        for (int ys = 0; ys < H; ys++) {
            for (int xs = 0; xs < W; xs++) {
                if (!visit[ys][xs] && m[ys][xs] == 1) {
                    visit[ys][xs] = true;
                    q.add(new Point(ys,xs));

                    List<Point> subres = new ArrayList<>();
                    while (!q.isEmpty()) {
                        Point p = q.remove();
                        subres.add(p);

                        for (int i = 0; i < offsets.length; i++) {
                            int y = p.y + offsets[i][0];
                            int x = p.x + offsets[i][1];

                            if (x >= 0 && x < W && y >= 0 && y < H && !visit[y][x] && m[y][x] == 1) {

                                visit[y][x] = true;
                                q.add(new Point(x, y));
                            }
                        }
                    }

                    res.add(subres);
                }
            }
        }

        return res;
    }
}
