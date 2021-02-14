package amazon.Graph;

import java.util.PriorityQueue;

public class Leet499_TheMazeIII {
    static class PointWithPath {
        int x;
        int y;
        int steps;
        StringBuilder path;
        public PointWithPath(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.path = new StringBuilder();
        }
    }
    public static String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        /*
        -> return the shortest distance
        start point: ball
        end point: hole
        -> BFS
            intial state: ball's start point
            next state:
                four directions:
                    up
                    down
                    left
                    right
                    -> ball stop point
                    -> continuously go up until reach the wall
                note:
                    if hole is on this path, return current steps
        */
        PriorityQueue<PointWithPath> pq = new PriorityQueue<>((a, b) -> {
            int res = a.steps - b.steps;
            if (res == 0) {
                return a.path.toString().compareTo(b.path.toString());
            }
            return res;
        });
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        visited[ball[0]][ball[1]] = true;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        String[] d = {"d", "u", "r", "l"};
        pq.offer(new PointWithPath(ball[0], ball[1], 0));
        while (!pq.isEmpty()) {
            PointWithPath cur = pq.poll();
            if (cur.x == hole[0] && cur.y == hole[1]) {
                return cur.path.toString();
            }

            for (int i = 0; i < 4; i++) {
                int s = 0;
                int x = cur.x;
                int y = cur.y;
                while (x + dir[i][0] >= 0 && y + dir[i][1] >= 0 && x + dir[i][0] < n && y + dir[i][1] < m
                        && maze[x + dir[i][0]][y + dir[i][1]] == 0 && !visited[x + dir[i][0]][y + dir[i][1]]) {
                    if (x == hole[0] && y == hole[1]) {
                        break;
                    }
                    x += dir[i][0];
                    y += dir[i][1];
                    s++;
                }
                if ((x + dir[i][0] < 0 || y + dir[i][1] < 0 || x + dir[i][0] == n || y + dir[i][1] == m || maze[x + dir[i][0]][y + dir[i][1]] == 1) && !visited[x][y]) {
                    visited[x][y] = true;
                    PointWithPath newOne = new PointWithPath(x, y, cur.steps + s);
                    newOne.path.append(cur.path).append(d[i]);
                    pq.offer(newOne);
                }
            }
        }
        return "impossible";
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0},
        };
        int[] ball = {4, 3};
        int[] hole = {0, 1};
        System.out.println(findShortestWay(maze, ball, hole));
    }
}
