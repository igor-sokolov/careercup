import java.util.Arrays;
import java.util.Random;

/**
 * Created by Igor_Sokolov on 6/16/2015.
 */
public class CareerCup_5766344614084608 {
    public static void main(String[] args) {
        int[] a = prepareData(10);

        System.out.println(Arrays.toString(a));

        int pos = binarySearch(a);

        System.out.print(pos);
    }

    private static int binarySearch(int[] a) {
        if (a[0] == 1) {
            return 0;
        }

        if (a[a.length - 1] == 0) {
            return a.length - 1;
        }

        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] < 1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return l;
    }

    private static int[] prepareData(int N) {
        final Random rand = new Random();

        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = rand.nextBoolean() ? 1 : 0;
        }

        Arrays.sort(a);
        return a;
    }
}
