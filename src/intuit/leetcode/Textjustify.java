package intuit.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Textjustify {
        public static List<String> fullJustify(String[] words, int maxWidth) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                int count = 1;
                int len = words[i].length();
                while (i + 1 < words.length && len + words[i + 1].length() + count <= maxWidth) {
                    i++;
                    len += words[i].length();
                    count++;
                }
                res.add(organize(words, len, i, count, maxWidth));
            }
            return res;
        }

        private static String organize(String[] words, int len, int endIndex, int count, int maxWidth) {
            String res = "";
            if (count - 1 <= 0) {
                if (count == 1) {
                    res += words[endIndex];
                    for (int i = 0; i < maxWidth - len; i++) {
                        res += " ";
                    }
                }
                return res;
            }
            int space = (maxWidth - len) / (count - 1);
            int mod = (maxWidth - len) % (count - 1);
            for (int i = endIndex - count + 1; i <= endIndex; i++) {
                res += words[i];
                if (i == endIndex) {
                    break;
                }
                for (int j = 0; j < space + mod; j++) {
                    res += " ";
                }
                mod = 0;
            }
            return res;
        }

        public static void main(String[] args) {
            String[] words = {"What","must","be","acknowledgment","shall","be"};
            int maxWidth = 16;
            List<String> res = fullJustify(words, maxWidth);
            for (String s : res) {
                System.out.println(s);
            }
        }
}
