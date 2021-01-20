package amazon.String;

public class Leet681_NextClosestTime {
    public String nextClosestTime(String time) {
       /*
       19:34
       12:33
       12:09 -> 12:10
       if last == 9
               check string has 0 and last bit + 1

          last - 1  != 5
       start from time.length - 1
           find number > than current bit
       */
        int[] values = new int[time.length() - 1];
        char[] arr = time.toCharArray();
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                values[index++] = arr[i] - '0';
            }
        }
        int s = existsLarger(arr[arr.length - 1] - '0', values, 's', ' ');
        if (s != -1) {
            arr[arr.length - 1] = (char)('0' + s);
            return new String(arr);
        }
        int m = existsLarger(arr[arr.length - 2] - '0', values, 'm', ' ');
        if (m != -1) {
            arr[arr.length - 2] = (char)('0' + m);
            changeToSmall(arr, values,arr.length - 1);
            return new String(arr);
        }
        int h1 = existsLarger(arr[1] - '0', values, 'h', arr[0]);
        if (h1 != -1) {
            arr[arr.length - 4] = (char)('0' + h1);
            changeToSmall(arr, values,arr.length - 2);
            return new String(arr);
        }
        int h2 = existsLarger(arr[0] - '0', values, 'H', ' ');
        if (h2 != -1) {
            arr[0] = (char)('0' + h2);
            changeToSmall(arr, values, 1);
            return new String(arr);
        }
        changeToSmall(arr, values, 1);
        return new String(arr);
    }

    private void changeToSmall(char[] arr, int[] values, int start) {
        int smallest = values[0];
        for (int i : values) {
            smallest = Math.min(smallest, i);
        }
        for (int i = start; i < arr.length; i++) {
            if (arr[i] == ':') continue;
            arr[i] = (char)('0' + smallest);
        }
    }

    private int existsLarger(int target, int[] values, char type, char h2) {
        int res = Integer.MAX_VALUE;
        int higherBound = type == 's' ? 9 : type == 'm' ? 5 : type == 'H' ? 2 : type == 'h' && h2 == '2' ? 4 : 9;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > target && values[i] <= higherBound) {
                res = Math.min(res, values[i]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
