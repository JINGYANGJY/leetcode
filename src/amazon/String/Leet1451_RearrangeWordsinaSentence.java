package amazon.String;

import java.util.Arrays;

public class Leet1451_RearrangeWordsinaSentence {
    public String arrangeWords(String text) {
        String[] arr = text.split(" ");
        Arrays.sort(arr, (a, b) -> {
            int res = a.length() - b.length();
            return res;
        });
        StringBuilder str  = new StringBuilder();
        for (String s : arr) {
            if (str.length() == 0) {
                char[] chars = s.toCharArray();
                StringBuilder cur = new StringBuilder();
                for (char c : chars) {
                    if (cur.length() == 0 && Character.isLowerCase(c)) {
                        cur.append(String.valueOf((char)('A' + c - 'a')));
                    } else {
                        cur.append(String.valueOf(c));
                    }
                }
                str.append(cur.toString());
            } else {
                str.append(s.toLowerCase());
            }
            str.append(" ");
        }
        String res = str.toString().trim();
        return res;
    }
}
