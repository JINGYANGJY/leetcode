package interview.medium;
/*
Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string],
where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid;
No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits
are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
3[a]2[bc]

 */
public class decodeString {
    /*

     input: String input encoding with number and data
     output: String after decoding
     Assumption: all input are just like 3[a] not 3a
                 the original data does not contains digit, digit are only for repeat numbers
     example: 3[a2[bc]]
         Sol: small problem --> 2[bc]
              DFS
              for each string
                1. get repeat times
                2. get data string
                3. construct repeat string
                4. return it
                construct repeated string and return

        Time:
            Assume we have O(maxK * n)
     */
    public static String decode(String input) {
        char[] arr = input.toCharArray();
        return dfs(arr);
    }
    static int index = 0;
    private static String dfs(char[] arr) {
        StringBuilder str = new StringBuilder();
        int num = 0;
        for (int i = index; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                num = num * 10 + arr[i] - '0';
            } else if (Character.isLetter(arr[i])) {
                str.append(arr[i]);
            } else if (arr[i] == '[') {
                i++;
                index = i;
                String sub = dfs(arr);
                while (num > 0) {
                    str.append(sub);
                    num--;
                }
                i = index;
            } else if (arr[i] == ']') {
                index = i;
                return str.toString();
            }
        }
        return str.toString();
    }
    public static void main(String[] args) {
        System.out.println(decode("3[a]2[bc]"));
    }
}
