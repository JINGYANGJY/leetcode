package intuit.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet465_OptimalAccountBalancing {
    public int transactions(int[][] transactions) {
        /*
        - get every person's account balance
        - Map<Integer, Integer>
        - settle up
                [1, 2, 3, -3, -2, -1]
             -> minimal transactions
                compare all possible transactions, find the minimal one
                DFS
               [1, 2, 3, -3, -2, -1]
                                1
                                  \        \         \
                        2    3     -3      -2        -1
                                   -2
               [0, 2, 3 ,-2, -2]
              for each level we, try all possible ways to settle current level's account
              Time: O(N + M!)
              N is number of transactions
              M is the number of accounts whose balance is not 0
         */
        Map<Integer, Integer> account = new HashMap<>();
        for (int[] transaction : transactions) {
            account.put(transaction[0], account.getOrDefault(transaction[0], 0) - transaction[2]);
            account.put(transaction[1], account.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        List<Integer> balances = new ArrayList<>();
        for (Integer k : account.keySet()) {
            balances.add(account.get(k));
        }
        return settleUp(0, balances);
    }

    private int settleUp(int index, List<Integer> balances) {
        while (index < balances.size() && balances.get(index) == 0) {
            index++;
        }
        if (index == balances.size()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int cur = balances.get(index);
        for (int i = index + 1; i < balances.size(); i++) {
            int next = balances.get(i);
            if (cur * next < 0) {
                balances.set(i, balances.get(i) + cur);
                min = Math.min(min, 1 + settleUp(index + 1, balances));
                balances.set(i, balances.get(i) - cur);
            }
        }
        return min;
    }
}
