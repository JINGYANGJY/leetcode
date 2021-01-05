package DFS.Memorization;

import java.util.TreeMap;

public class Leet726NumberOfAtoms {
    //特别垃圾的方法
    public String countOfAtoms(String formula) {
        /*
        Divide and conquer
            ON(SO3)2
            if ('(' || i == 0)
                dfs()
                 base case: if (')' || i == length)

                 return String
        */
        char[] arr = formula.toCharArray();
        int[] index = new int[1];
        String cnt = dfs(index, arr);
        // System.out.println(cnt);
        return buildRes(cnt);
    }

    private String buildRes(String input) {
        TreeMap<String, Integer> map = new TreeMap<>((a, b) -> {
            return a.compareTo(b);
        });
        char[] arr = input.toCharArray();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isUpperCase(arr[i])) {
                if (key.length() > 0) {
                    map.put(key.toString(), map.getOrDefault(key.toString(), 0) + 1);
                    key = new StringBuilder();
                }
                key.append(String.valueOf(arr[i]));
            } else if (Character.isLowerCase(arr[i])) {
                key.append(String.valueOf(arr[i]));
            } else if (Character.isDigit(arr[i])) {
                int num = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    num *= 10;
                    num += arr[i] - '0';
                    i++;
                }
                i -= 1;
                map.put(key.toString(), map.getOrDefault(key.toString(), 0) + num);
                key = new StringBuilder();
            }
        }
        if (key.length() > 0) {
            map.put(key.toString(), map.getOrDefault(key.toString(), 0) + 1);
            key = new StringBuilder();
        }
        StringBuilder res = new StringBuilder();
        for (String s : map.keySet()) {
            res.append(s);
            int count = map.get(s);
            if (count == 1) {
                continue;
            } else {
                res.append(String.valueOf(count));
            }

        }
        return res.toString();
    }

    private String dfs(int[] index, char[] arr) { //index的改变要注意
        StringBuilder sb = new StringBuilder();
        String cnt = "";
        for (int i = index[0]; i < arr.length; i++) {
            if (arr[i] == '(') {
                index[0] = i + 1;
                cnt = dfs(index, arr);
                sb.append(cnt);// 为了test case(F)(H)4
                i = index[0] - 1;
            } else if (Character.isLetter(arr[i])) {
                while (i < arr.length && (arr[i] != ')' && arr[i] != '(')) {
                    sb.append(String.valueOf(arr[i]));
                    i++;
                }
                i -= 1;
            } else if (Character.isDigit(arr[i])) {
                int num = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    num = num * 10 + arr[i] - '0';
                    i++;
                }
                i -= 1;
                while (num > 1) { // 因为之前append了一次cnt， 因此num > 1 而不是0
                    sb.append(cnt);
                    num--;
                }
            } else if (arr[i] == ')') {
                index[0] = i + 1;
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
