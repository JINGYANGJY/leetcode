package google;

public class Leet1616_SplitTwoStringstoMakePalindrome {
    public boolean checkPalindromeFormation(String a, String b) {
        /*
        step 1:
            find the common string
            start from index 0 of a   left to right
            start from index b.length - 1 of b right to left
        step 2:
            check middle part is palindrome or not
        */
        int n = a.length();
        return findCommonSubstring(a.toCharArray(), b.toCharArray(), 0, n - 1) ||
                findCommonSubstring(b.toCharArray(), a.toCharArray(), 0, n - 1);
    }
    private boolean isPanlidrome(char[] arr, int left, int right) {
        while (left <= right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean findCommonSubstring(char[] arrA, char[] arrB, int left, int right) {
        while (left < arrA.length && right >= 0) {
            if (arrA[left] == arrB[right]) {
                left++;
                right--;
            } else {
                break;
            }
        }
        if (isPanlidrome(arrA, left, right) || isPanlidrome(arrB, left, right)) {
            return true;
        }
        return false;
    }
}
