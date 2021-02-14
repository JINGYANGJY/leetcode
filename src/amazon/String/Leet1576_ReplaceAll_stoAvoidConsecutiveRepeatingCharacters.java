package amazon.String;

public class Leet1576_ReplaceAll_stoAvoidConsecutiveRepeatingCharacters {
    public String modifyString(String s) {
        /*
        _?_ =>
            for each index
                left
                right
                start from 'a'
                find the first element which is not == left && not == right
        */
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char left = i == 0 ? ' ' : arr[i - 1];
            char right = i == arr.length - 1 ? ' ' : arr[i + 1];
            if (arr[i] == '?') {
                for (char x = 'a'; x <= 'z'; x++) {
                    if (x != left && x != right) {
                        arr[i] = x;
                        break;
                    }
                }
            }
        }
        return new String(arr);
    }
}
