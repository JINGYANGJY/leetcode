package DFS.Recursion;

import java.util.ArrayList;
import java.util.List;

public class Leet779K_thSymbolinGrammar {
    public int kthGrammar(int N, int K) {
        /*
            construct each row
            0
            10
            0110
            ...
            Time:O(2^(N- 1))
            Space: O(2^(N- 1))
            ------------------------------------
            optimization:
                        DFS
                          1
                        /   \
                       2     3
                      / \    /\
                     4   5  6  7
            root
            left = root * 2
            right = root * 2 + 1
            N level K == 2^(N - 1) + K - 1
            e.g. N = 4 K = 5
                 the fourth level, 5th nodes in the completed tree
            from current node, find its parent node until the root of the tree

            e.g.
             N = 12
             parent = 6
             parent.parent = 3
             parent.parent.parent = 1

             if val % 2 == 0
                    p = val / 2;
                else
                    p = (val - 1) / 2
            start from 1, we know its child is left or right
            if p = 0
               p.left = 1
               p.right = 0
              ...
        Time: O(logN)
        */
        int leaf = (int)Math.pow(2, N- 1) + K - 1;
        List<Integer> path = new ArrayList<>();
        path.add(leaf);
        while (leaf != 1) {
            if (leaf % 2 == 0) {
                leaf = leaf / 2;
            } else {
                leaf = (leaf - 1)  / 2;
            }
            path.add(leaf);
        }
        int initial = 0;
        int prev = 1;
        for (int i = path.size() - 2; i >= 0; i--) {
            int cur = path.get(i);
            if (cur == prev * 2) {
                initial = initial == 0 ? 0 : 1;
            } else {
                initial = initial == 0 ? 1 : 0;
            }
            prev = cur;
        }
        return initial;
    }
}
