package amazon.String;

public class Leet680_ValidPalindromeII {
    public boolean validPalindrome(String s) {
        /*
        goals to check if the s is palindrome with at most delete one character
        ->
        left = 0, right = s.length - 1
            check if it is palidrome
                if ==
                    left++;
                    right--;
                otherwise
                    two choices:
                        delete left one
                             ||
                        delete right one

                        after deletion, the left substring is panlidrome, return true
        */
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (arr[left] != arr[right]) {
                return isPanlindrome(arr, left + 1, right) || isPanlindrome(arr, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPanlindrome(char[] arr, int left, int right) {
        while (left <= right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
