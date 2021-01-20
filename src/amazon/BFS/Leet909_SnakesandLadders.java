package amazon.BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Leet909_SnakesandLadders {
    public int snakesAndLadders(int[][] board) {
        /*
        start at 1
        -> the least number of moves to reach square N*N
        each move
            if (board[r][c] == -1)
                next X + 1 .... X + 6
            otherwise
                next -> board[r][c]
        BFS
            -> initial state 1
            -> expand from the queue by level
            -> generate
                if (board[r][c] == -1)
                next X + 1 .... X + 6
            otherwise
                next -> board[r][c]
        */
        int N = board.length;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        int res = 0;
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer curSquare = queue.poll();
                if (curSquare == N * N) {
                    return res;
                }
                if (set.contains(curSquare)) {
                    continue;
                }
                set.add(curSquare);
                for (int dis = 1; dis <= 6 && curSquare + dis <= N * N; dis++) {
                    int val = getValFromBoard(curSquare + dis, board);
                    val = val == -1 ? curSquare + dis : val;
                    if (!set.contains(val)) {
                        queue.offer(val);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private int getValFromBoard(int num, int[][] board) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - 1 - r;
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        return board[x][y];
    }
}
