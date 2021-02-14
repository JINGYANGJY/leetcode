package amazon.HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet957_PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> map = new HashMap<>();
        while (N > 0) {
            String key = Arrays.toString(cells);
            map.put(key, N--);
            cells = process(cells);
            if (map.containsKey(Arrays.toString(cells))) {
                N = N % (map.get(Arrays.toString(cells)) - N);
            }
        }
        return cells;
    }

    private int[] process(int[] cells) {
        int[] res = new int[8];
        for (int i = 1; i < 7; i++) {
            res[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return res;
    }
}
