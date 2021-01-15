package amazon.oa;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Turnslite {
    /*
    Given an time array, time[i] represents ith students reach turnslite at time[i]
    direction array, direction[i] == 1 exit, direction[i] == 0 enter
    at the same time, exit first
    previous enter, then enter
    previous exit, then exit
     */

    public static int[] timeLeave(int[] time, int[] direction) {
        /*
        1. separate into two queue
           e.g [0,0,1,5] direction[0,1,1,0]
           exit: 0, 1
           enter: 0, 5
        2. p q
           p:  points to the current student of exit queue
           q: points to the current ....enter queue
        int current time
        int previous direction

        case 1:
            p < current time && q < current time
              poll student from exit first

        case 2:
            if p == current || q == current
                the other < current
                if (previous == 1)
                        poll from exit
                else
                        poll from enter

       case 3: p > current && q > current time
            p <= q
                do exit
            else
                do enter
         */
        Deque<Student> exit = new ArrayDeque<>();
        Deque<Student> enter = new ArrayDeque<>();
        for (int i = 0; i < time.length; i++) {
            if (direction[i] == 1) {
                exit.offer(new Student(i, time[i]));
            } else {
                enter.offer(new Student(i, time[i]));
            }
        }
        int[] res = new int[time.length];
        int currentTime = 0;
        int prevDir = 1;
        while (!exit.isEmpty() && !enter.isEmpty()) {
            Student s1 = exit.peek();
            Student s2 = enter.peek();
            if (prevDir == 1) {
                if (s2.time < s1.time) {
                    res[s2.id] =  s2.time > currentTime ? s2.time : currentTime;
                    enter.poll();
                    currentTime = s2.time > currentTime ? s2.time  + 1: currentTime + 1;
                    prevDir = 0;
                } else {
                    res[s1.id] = s1.time > currentTime ? s1.time : currentTime;
                    exit.poll();
                    currentTime = s1.time > currentTime ? s1.time + 1 : currentTime + 1;
                }
            } else {
                if (s1.time < s2.time) {
                    res[s1.id] = s1.time > currentTime ? s1.time : currentTime;
                    exit.poll();
                    currentTime = s1.time > currentTime ? s1.time  + 1 : currentTime + 1;
                    prevDir = 1;
                } else {
                    res[s2.id] = s2.time > currentTime ? s2.time : currentTime;
                    enter.poll();
                    currentTime = s2.time > currentTime ? s2.time + 1 : currentTime + 1;
                }
            }
        }
        while (!exit.isEmpty()) {
            Student s1 = exit.poll();
            res[s1.id] = currentTime > s1.time ? currentTime : s1.time;
            currentTime = currentTime > s1.time ? currentTime + 1 : s1.time + 1;
        }
        while (!enter.isEmpty()) {
            Student s2 = enter.poll();
            res[s2.id] = currentTime > s2.time ? currentTime : s2.time;
            currentTime = currentTime > s2.time ? currentTime  + 1 : s2.time + 1;
        }
        return res;
    }
    static class Student {
        int id;
        int time;
        public Student(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
    public static void main(String[] args) {
        int[] time = {0, 0, 1, 5};
        int[] direction = {0, 1, 1, 0};
        int[] res = timeLeave(time, direction);
        System.out.println(Arrays.toString(res));

        time = new int[]{1, 2, 4};
        direction = new int[]{0, 1, 1};
        res = timeLeave(time, direction);
        System.out.println(Arrays.toString(res));

        time = new int[]{1, 1};
        direction = new int[]{1, 1};
        res = timeLeave(time, direction);
        System.out.println(Arrays.toString(res));

        time = new int[]{1, 1, 3, 3, 4, 5, 6, 7, 7};
        direction = new int[]{1, 1, 0, 0, 0, 1, 1, 1, 1};
        res = timeLeave(time, direction);
        System.out.println(Arrays.toString(res));
    }
}
