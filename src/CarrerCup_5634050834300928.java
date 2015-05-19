import java.io.File;
import java.io.IOException;

/**
 * Created by Igor_Sokolov on 3/12/2015.
 */
public class CarrerCup_5634050834300928 {
    public static void main(String[] args) throws IOException {
        String tests[] = {"e/../../a/d/./../b/c", "/etc/../.././../usr/", "C:/..Windows/./systems32/../../temp/."};
        for (int i = 0; i < tests.length; i++) {
            String path = getCanonicalPath(tests[i]);
            System.out.println(path);
        }
    }

    private static String getCanonicalPath(String line) {
        if (line.isEmpty()) {
            throw new RuntimeException("invalid path");
        }

        String root = extractRoot(line);
        if (root != null) {
            line = line.substring(root.length());
        }

        String[] terms = line.split("/");

        int j = 0;
        String[] result = new String[terms.length];

        int stepUp = 0;
        int i = terms.length - 1;
        while (i >= 0) {
            final String term = terms[i--];
            if (term == null || term.isEmpty()) {
                throw new RuntimeException("invalid path");
            }

            if (term.equals(".")) {
                continue;
            }

            if (term.equals("..")) {
                stepUp++;
                continue;
            }

            if (stepUp != 0) {
                stepUp--;
            } else {
                result[j++] = term;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root);
        }

        for (int k = j - 1; k >= 0; k--) {
            if (k != j - 1) {
                sb.append('/');
            }

            sb.append(result[k]);
        }

        return sb.toString();
    }

    private static String extractRoot(String line) {
        int pos = line.indexOf('/');
        // we assume this is unix path, for example "/etc/hosts"
        if (pos == 0) {
            return "/";
        }

        // maybe this is windows path, for example "C:/widows/system32"
        String root = line.substring(0, pos + 1);
        if (root.contains(":")) {
            return root;
        }

        return null;
    }


}
