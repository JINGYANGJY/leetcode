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

    public String[] reorderLogFiles2(String[] logs) {
        /*
            1. letter logs > digits logs
            2. letter logs
                    2.1 content
                    2.2 identifier
            3. digits relative orders
            implements the sort rules
        */
        Arrays.sort(logs, (a, b) -> {
            String[] arrA = a.split(" ");
            String[] arrB = b.split(" ");
            boolean isLetterA = check(arrA[1]);
            boolean isLetterB = check(arrB[1]);
            if (isLetterA && isLetterB) {
                int i = 1;
                for (; i < Math.min(arrA.length, arrB.length); i++) {
                    if (arrA[i].equals(arrB[i])) {
                        continue;
                    } else {
                        return arrA[i].compareTo(arrB[i]);
                    }
                }
                // content are same check identifier
                if (i == arrA.length && i == arrB.length) {
                    return arrA[0].compareTo(arrB[0]);
                } else if (i == arrA.length) {
                    return -1;
                } else {
                    return 1;
                }
                // both are digit logs
            } else if (!isLetterA && !isLetterB) {
                return 0;
            } else {
                return isLetterA ? -1 : 1;
            }
        });
        return logs;
    }

    private boolean check(String s) {
        if (s.length() < 0) {
            return false;
        }
        return Character.isDigit(s.charAt(0)) ? false : true;
    }
}
