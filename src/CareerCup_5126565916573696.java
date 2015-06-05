import java.util.BitSet;

/**
 * Created by sis on 5/31/15.
 */
public class CareerCup_5126565916573696 {
    public static void main(String[] args) {
        new Problem(5).naive();
    }

    static class Problem {
        private int length;
        private final BitSet visited;
        private String previousString;

        private int totalKeys;
        private int duplications;
        private int maxValue;

        Problem(int length) {
            this.length = length;
            this.maxValue = (int) Math.pow(10.0, length);
            this.visited = new BitSet(maxValue);
        }

        private void innerCheck(String currentString) {
            if (previousString != null) {
                String concat = previousString + currentString;
                for (int i = 1; i < concat.length() - length; i++) {
                    checkAndVisit(concat.substring(i, i + length));
                }
            }

            checkAndVisit(currentString);
            previousString = currentString;
        }

        private void checkAndVisit(String str) {
            int value = Integer.valueOf(str);
            if (visited.get(value)) {
//                uncomment this line to see duplicated values
//                System.out.printf("!!!! duplication: %05d %n", value);
                duplications++;
            } else {
                visited.set(value, true);
            }
        }

        public void naive() {
            final String fmt = "%0" + length + "d";

            for (int i = 0; i < maxValue; i++) {
                if (!visited.get(i)) {

                    String str = String.format(fmt, i);
                    innerCheck(str);

                    totalKeys += length;
                }
            }

            System.out.printf("Total duplications: %d%n", duplications);
            System.out.printf("Total keys: %d%n", totalKeys);
        }
    }
}
