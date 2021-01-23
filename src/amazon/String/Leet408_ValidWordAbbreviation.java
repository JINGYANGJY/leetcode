package amazon.String;

public class Leet408_ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        char[] wordArray = word.toCharArray();
        int index = 0;
        char[] abbrArray = abbr.toCharArray();
        for (int i = 0; i < abbrArray.length; i++) {
            if (index < wordArray.length && Character.isLetter(abbrArray[i]) && wordArray[index] != abbrArray[i]) {
                return false;
            } else if (Character.isDigit(abbrArray[i])) {
                if (abbrArray[i] == '0') return false;
                int cur = 0;
                while (i < abbrArray.length && Character.isDigit(abbrArray[i])) {
                    cur = cur * 10 + abbrArray[i++] - '0';
                }
                i -= 1;
                index += cur;
            } else {
                index++;
            }
        }
        return index == wordArray.length;
    }
}
