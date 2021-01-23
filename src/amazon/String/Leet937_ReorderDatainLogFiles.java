package amazon.String;

import java.util.Arrays;

public class Leet937_ReorderDatainLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            String[] arrA = a.split(" ");
            String[] arrB = b.split(" ");
            for (int i = 1; i < Math.min(arrA.length , arrB.length); i++) {
                if (arrA[i].equals(arrB[i])) {
                    continue;
                }
                if (Character.isDigit(arrA[i].charAt(0)) && Character.isDigit(arrB[i].charAt(0))) {
                    return 0;
                } else if (Character.isLetter(arrA[i].charAt(0)) && Character.isLetter(arrB[i].charAt(0))) {
                    return arrA[i].compareTo(arrB[i]);
                } else {
                    return Character.isLetter(arrA[i].charAt(0)) ? -1 : 1;
                }
            }
            return arrA[0].compareTo(arrB[0]);
        });
        return logs;
    }
}
