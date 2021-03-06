package interval;

import java.util.ArrayList;
import java.util.List;

/**
 *Clarifications:
 * 	1. the given intervals are sorted or not?
 * 	2. both start and end point are included?
 * 	3. return a new int[][]?
 * 	4. if newInterval has non overlapping with any one in the intervals, do i need to add it into res?
 * Assumption:
 * 	the given intervals are sorted by start point
 *
 * M1:
 * 		add newInterval into intervals
 * 		sort
 * 		merge
 * 		Time: O(nlogn + n)
 * M2:
 * 		intervals[i] relationship with newInterval
 * 		1.  XXXXXXX
 * 		        NNNNNNN
 * 		2.      XXXXXXX
 * 	        NNNNNNN
 *
 * 		3. XXXXXX
 * 				    NNNNN
 * 		4.            XXXXXX
 * 		        NNNN
 * 		5. XXXXXXXX
 * 		      NNN
 * 		6.    XXX
 * 		    NNNNNNNNN
 * Solution steps:
 * 		if (3)
 * 			add to res
 * 	    else if (4)
 * 	        if (!merge)
 * 	            add newInterval to res
 * 			add to res
 * 		else
 * 			if (!merge)
 * 				create a new intervals[i] with newInterval
 * 				add to res
 * 			else
 * 				update the res last one
 * 		Time: O(n)
 */

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        boolean merge = false;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0] ) {
                res.add(intervals[i]);
            } else if (intervals[i][0] > newInterval[1]) {
                if (!merge) { // ❌但是改过来的点
                    res.add(newInterval);
                    merge = true;
                }
                res.add(intervals[i]);
            } else {
                //我的写法
//                if (!merge) {
//                    int[] addToRes = new int[]{Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])};
//                    res.add(addToRes);
//                    merge = true;
//                } else {
//                    int[] last = res.get(res.size() - 1);
//                    last[1] = Math.max(intervals[i][1], last[1]);
//                }
                //老师的写法
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }
        if (!merge) { // ❌但是改过来的点
            res.add(newInterval);
        }
        return res.toArray(new int[res.size()][]);
    }
}
