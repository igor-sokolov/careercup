import java.util.HashSet;

/**
 * Created by sis on 5/27/15.
 */
public class CareerCup_5758677862580224 {
    public static void main(String[] args) {
        int[] a = {-1, 4, 5, -23, 24};
        int m = getNotPresentedMin(a);
        System.out.println(m);
    }

    private static int getNotPresentedMin(int[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException();
        }

        HashSet<Integer> real = new HashSet<>();
        HashSet<Integer> potential = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            real.add(a[i]);
            potential.add(a[i] + 1);
        }

        int min = Integer.MAX_VALUE;
        for (int i: potential) {
            if (!real.contains(i)) {
                min = Math.min(min, i);
            }
        }

        return min;
    }
}
