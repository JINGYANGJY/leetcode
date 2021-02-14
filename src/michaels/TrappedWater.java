package michaels;

import java.util.PriorityQueue;

public class TrappedWater {
    /*

     */
    class Cell {
        int x;
        int y;
        int height;
        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0) return 0;
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> {
            int res = a.height - b.height;
            return res;
        });
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            if (!visited[i][0]) {
                visited[i][0] = true;
                pq.offer(new Cell(i, 0, heightMap[i][0]));
            }
            if (!visited[i][m - 1]) {
                visited[i][m - 1] = true;
                pq.offer(new Cell(i, m - 1, heightMap[i][m - 1]));
            }
        }

        for (int j = 0; j < m; j++) {
            if (!visited[0][j]) {
                visited[0][j] = true;
                pq.offer(new Cell(0, j, heightMap[0][j]));
            }
            if (!visited[n - 1][j]) {
                visited[n - 1][j] = true;
                pq.offer(new Cell(n - 1, j, heightMap[n - 1][j]));
            }
        }
        int res = 0;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.isEmpty()) {
            Cell lowest = pq.poll();
            for (int[] d : dir) {
                int dx = d[0] + lowest.x;
                int dy = d[1] + lowest.y;
                if (dx < 0 || dy < 0 || dx == n || dy == m || visited[dx][dy]) continue;
                visited[dx][dy] = true;
                if (heightMap[dx][dy] < lowest.height) {
                    res += lowest.height - heightMap[dx][dy];
                }
                pq.offer(new Cell(dx, dy, Math.max(lowest.height, heightMap[dx][dy])));
            }
        }
        return res;
    }
}
