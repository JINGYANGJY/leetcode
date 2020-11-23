package interview.medium;

import java.util.*;

/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks.
The bricks have the same height but different width.
You want to draw a vertical line from the top to the bottom and cross the least bricks.
The brick wall is represented by a list of rows.
Each row is a list of integers representing the width of each brick in this row from left to right.
If your line go through the edge of a brick, then the brick is not considered as crossed.
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
You cannot draw a line just along one of the two vertical edges of the wall,
in which case the line will obviously cross no bricks.
 */
public class BrickWall {
    class Entry {
        int sum;
        int id;
        int index;
        public Entry(int sum, int id, int index) {
            this.sum = sum;
            this.id = id;
            this.index = index;
        }
    }
    public int leastBricks(List<List<Integer>> wall) {
    /*
        clarification:
            the sum of brick wall won't exceed Integer.MAX_VALUE
        we want to know at each point, how many walls have edge
        start from 0
            add all rows wall into a data structure pq
            check at cur point, how many walls have edge
            poll them from the pq, add next brick into pq
     */
        int edge = 0;
        for (int i = 0; i < wall.get(0).size(); i++) {
            edge += wall.get(0).get(i);
        }
        PriorityQueue<Entry> pq = new PriorityQueue<>((a, b) -> (a.sum - b.sum));
        for(int i = 0; i < wall.size(); i++) {
            pq.offer(new Entry(wall.get(i).get(0), i, 0));
        }
        int cur = pq.peek().sum;
        int res = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            if (e.sum == cur) {
                count++;
            } else {
                if (cur != edge) {
                    res = Math.max(res, count);
                }
                count = 1;
                cur = e.sum;
            }
            if (e.index + 1 < wall.get(e.id).size()) {
                pq.offer(new Entry(e.sum + wall.get(e.id).get(e.index + 1), e.id, e.index + 1));
            }
        }
        return wall.size() - res;
    }
}
