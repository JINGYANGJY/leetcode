package amazon.String;

public class Leet443_StringCompression {
    public int compress(char[] chars) {
        /*
        find the consecutive char
        if size > 1
        res += 1 + String.valueOf(num).length()

        */
        int index = 0;
        char prev = chars[index++];
        int count = 1;
        int slow = 0;
        while (index <= chars.length) {
            if (index < chars.length && chars[index] == prev) {
                count++;
            } else {
                chars[slow++] = prev;
                if (index < chars.length) prev = chars[index];
                if (count > 1) {
                    char[] nums = String.valueOf(count).toCharArray();
                    for (int i = 0; i < nums.length && slow < chars.length; i++) {
                        chars[slow++] = nums[i];
                    }
                }
                count = 1;
            }
            index++;
        }
        return slow;
    }
}
