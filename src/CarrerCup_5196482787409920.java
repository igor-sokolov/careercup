/**
 * Created by Igor_Sokolov on 3/12/2015.
 */
public class CarrerCup_5196482787409920 {
    public static int candleTurns(int[] candles) {
        if (candles.length == 0) {
            return 0;
        }

        countSort(candles);

        int turn = 0;
        while (true) {
            for (int i = 0; i < turn + 1; i++) {
                if (--candles[i] < 0) {
                    return turn;
                }
            }

            turn++;
            if (turn >= candles.length) {
                return turn;
            }

            countSort(candles);
        }
    }

    private static void countSort(int[] candles) {
        int[] counts = new int[candles.length];

        int last = 0;
        for (int i = 0; i < candles.length; i++) {
            int candle = candles[i];
            if (candle >= counts.length) {
                swap(candles, last++, i);
            } else {
                counts[candle]++;
            }
        }

        for (int i = counts.length - 1; i >= 0; i--) {
            int count = counts[i];
            if (count != 0) {
                for (int j = 0; j < count; j++) {
                    candles[last++] = i;
                }
            }
        }
    }

    private static void swap(int[] candles, int i, int j) {
        int t = candles[i];
        candles[i] = candles[j];
        candles[j] = t;
    }

    public static void main(String[] args) {
        int[] test1 = {};
        System.out.println(candleTurns(test1)); // 0

        int[] test2 = {1};
        System.out.println(candleTurns(test2)); // 1

        int[] test3 = {10};
        System.out.println(candleTurns(test3)); // 1

        int[] test4 = {2, 2, 2};
        System.out.println(candleTurns(test4)); // 3

        int[] test5 = {1, 2, 2};
        System.out.println(candleTurns(test5)); // 2

        int[] test6 = {1, 2, 3};
        System.out.println(candleTurns(test6)); // 3

        int[] test7 = {1, 2, 3, 4};
        System.out.println(candleTurns(test7)); // 4

        int[] test8 = {1, 2, 3, 3};
        System.out.println(candleTurns(test8)); // 3

        int[] test9 = {3, 3, 3, 3};
        System.out.println(candleTurns(test9)); // 4

    }
}
