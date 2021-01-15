package amazon.oa;

import java.util.TreeMap;

public class ItemsInContainer {
    /*
    Amazon would like to know how much inventory exists in their closed inventory compartments. Given a string s
consisting of items as "*" and closed compartments as an open and close "|",
an array of starting indices startIndices, and an array of ending indices endIndices,
determine the number of items in closed compartments within the substring between the two indices, inclusive.

An item is represented as an asterisk ('*' = ascii decimal 42)
A compartment is represented as a pair of pipes that may or may not have items between them ('|' = ascii decimal 124).
     */

    /*
    step 1:
        for each '|'
            get how many '*' begin it
                Map<Integer, Integer>
                    key is the '|''s index, value is the number of the '*'
   step 2:
        for each pair of compartment
            get the index of  '|' whose index equals or larger than start index
            get the index of  '|' whose index equals or smaller thant end index
        do substraction
     */
    public static int[] numberOfStar(String s, int[] startIndices, int[] endIndices) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        char[] arr = s.toCharArray();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '*') {
                count++;
            } else if (arr[i] == '|') {
                map.put(i + 1, count);
            }
        }
        int[] res = new int[startIndices.length];
        for (int i = 0; i < startIndices.length; i++) {
            Integer start = map.ceilingKey(startIndices[i]);
            Integer end = map.floorKey(endIndices[i]);
            if (start != null) {
                res[i] = map.get(end) - map.get(start);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "|****|";
        int[] startIndices = {1};
        int[] endIndices = {6};
        int[] res = numberOfStar(s, startIndices, endIndices);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
