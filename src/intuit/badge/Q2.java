package intuit.badge;

import java.util.*;

public class Q2 {
    /*
    We want to find employees who badged into our secured room unusually often. We have an unordered list of names and access times over a single day.
    Access times are given as three or four-digit numbers using 24-hour time, such as "800" or "2250".
    Write a function that finds anyone who badged into the room 3 or more times in a 1-hour period,
    and returns each time that they badged in during that period. (If there are multiple 1-hour periods where this was true, just return the first one.)
     - for each employee
            - Map<String, List<String>>

        for each employee
                sort list
                    for loop
                           check?
                                for each time
                                    check --
        Time: O(n + nlogn + n) - O(nlogn)
        n is length of the input
     */
    public static Map<String, List<String>> timeList(String[][] input) {
        Map<String, List<String>> res = new HashMap<>();
        Map<String, List<String>> timeList = new HashMap<>();
        for (String[] s : input) {
            String name = s[0];
            timeList.putIfAbsent(name, new ArrayList<>());
            timeList.get(name).add(s[1]);
        }
        for (String key : timeList.keySet()) {
            List<String> times = timeList.get(key);
            Collections.sort(times, (a, b) -> {
                int ans = Integer.valueOf(a) - Integer.valueOf(b);
                return ans < 0 ? -1 : 1;
            });
            List<String> list = moreTimesOneHour(times);
            if (list.size() > 0) {
                res.put(key, list);
            }
        }
        return res;
    }
    // 1800 1900
    // 18:20 19:20
    private static List<String> moreTimesOneHour(List<String> times) {
        List<String> res = new ArrayList<>();
        int len = 3;
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < times.size(); i++) {
            while (!queue.isEmpty() && !withInOneHour(queue.peekFirst(), times.get(i))) {
                queue.pollFirst();
            }
            queue.offerLast(times.get(i));
            if (queue.size() >= len) {
                len = queue.size();
                res.clear();
                res.addAll(queue);
            }
        }
        return res;
    }
    // 18:20 + 60  19:20
    private static boolean withInOneHour(String time1, String time2) {
        char[] arr = time1.toCharArray();
        int carry = 0;
        int cur = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (i == arr.length - 2) {
                cur = arr[i] - '0' + 6;
                carry = cur / 6;
                arr[i] = (char)('0' + cur % 6);
            } else {
                cur = arr[i] - '0' + carry;
                carry = cur / 10;
                arr[i] = (char)('0' + cur % 10);
            }
        }
        time1 = new String(arr);
        if (carry > 0) {
            time1 = String.valueOf(carry) + time1;
        }
        return Integer.valueOf(time1) >= Integer.valueOf(time2);
    }

    public static void main(String[] args) {
       String[][]  badge_records = new String[][] {

 {"Paul", "1355"},
 {"Jennifer", "2358"},

 {"John", "830"},

 {"Paul", "1315"},

 {"John", "835"},

 {"Paul", "1405"},

 {"Paul", "1630"},

 {"John", "855"},

 {"John", "915"},

 {"John", "940"},

 {"Jennifer", "2400"},

 {"Jennifer", "2359"},

 {"John", "1630"},

 };
        Map<String, List<String>> res = timeList(badge_records);
        for (String key : res.keySet()) {
            System.out.println(key);
            for (String s : res.get(key)) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

    }
}
