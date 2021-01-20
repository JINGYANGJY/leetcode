package amazon.String;

public class Leet541_ReverseStringII {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i += 2 * k) {
            int right = i + k - 1 < arr.length ? i + k - 1 : arr.length - 1;
            swap(arr, i, right);
        }
        return new String(arr);
    }

    private void swap(char[] arr, int left, int right) {
        while (left <= right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
