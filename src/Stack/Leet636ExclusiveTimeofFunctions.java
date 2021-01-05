package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
/*
写的很差 需要分析清楚再写一遍
 */
public class Leet636ExclusiveTimeofFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        /*
            case 1: S S
                    push into stack
                    record last job time
            case 2: S E
                    current job finished
                    calculate and record
            case 3: E E
                    calculate and recored
            case 4: E S
                    no need calculate
                    push into stack
        */
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        String prevType = null;
        Integer prevID = null;
        Integer prevTime = null;
        for (String log : logs) {
            String[] items = log.split(":");
            String type = items[1];
            int id = Integer.valueOf(items[0]);
            int time = Integer.valueOf(items[2]);
            if (prevID == null) {
                prevID = id;
                prevType = type;
                prevTime = time;
                stack.push(id);
                continue;
            }

            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    if (prevType.equals("end")) { //
                        res[stack.peek()] += time - prevTime - 1;
                    } else {
                        res[stack.peek()] += time - prevTime;
                    }

                }
                stack.push(id);
            } else {
                if (prevType.equals("start")) {
                    res[id] += time - prevTime + 1;
                } else {
                    res[id] += time - prevTime;
                }
                stack.pop();
            }
            prevID = id;
            prevType = type;
            prevTime = time;
        }
        return res;
    }
}
