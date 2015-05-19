import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Igor_Sokolov on 3/12/2015.
 */
public class CarrerCup_5727163577794560 {
    public static void main(String[] args) {
        int[][] tests = {{2, 5, 3, 7, 9}, {1, 10, 1, 9, 2, 3, 8, 12, 100, -12}, {}, {-1}};
        int sum = 11;

        for (int i = 0; i < tests.length; i++) {
            System.out.println("test #" + (i + 1));
            final List<int[]> pairs = findPairs(tests[i], sum);
            for (int[] pair: pairs) {
                System.out.printf("[%d, %d]%n", pair[0], pair[1]);
            }
        }
    }

    private static List<int[]> findPairs(int[] a, int sum) {
        final List<int[]> result = new ArrayList<>();

        final HashSet<Integer> set = new HashSet<>(a.length);
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }

        final HashSet<Integer> used = new HashSet<>(a.length);
        for (int i = 0; i < a.length; i++) {
            final int first = a[i];
            if (used.contains(first)) {
                continue;
            }

            final int second_positive = sum - first;
            if (set.contains(second_positive)) {
                result.add(new int[] {first, second_positive});

                used.add(first);
                used.add(second_positive);
            }

            final int second_negative = -first - sum;
            if (set.contains(second_negative)) {
                result.add(new int[] {first, second_negative});

                used.add(first);
                used.add(second_negative);
            }
        }

        return result;
    }


}
