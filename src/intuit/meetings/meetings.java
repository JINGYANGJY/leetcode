package intuit.meetings;

import java.util.*;

public class meetings {
    /*
    case1:
        [m1]
             [m2]
             m1.end <= m2.start
    case2:
             [m1]
       [m2]
            m2.end <= m1.start

            m1.end > m2.start && m2.end > m1.start
     */
    public static boolean hasConflicts(int[] m1, int[] m2) {
        if (m1[1] > m2[0] && m2[1] > m1[0]) {
            return false;
        }
        return true;
    }

    public static List<int[]> Q2(List<int[]> intervals) {
        /*
        free ->
            intervals which doesn't have conflict with interval in the list
            ->
                overlap
                merge interval overlap
                ->
                   sort the intervals
                   prev
                        [cur]
                        merge
                        cur

                ->
                find the excluded interval of the list
            Time:O(nlogn + n) -> O(nlogn)
         */

        Collections.sort(intervals, (a, b) -> {
            int res = a[0] - b[0];
            if (res == 0) {
                return a[1] - b[1];
            }
            return res;
        });

        List<int[]> list = new ArrayList<>();
        list.add(intervals.get(0));
        int[] prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            int[] cur = intervals.get(i);
            if (hasConflicts(prev, intervals.get(i))) {
                prev[0] = Math.min(prev[0], cur[0]);
                prev[1] = Math.max(prev[1], cur[1]);
            } else {
                list.add(cur);
                prev = cur;
            }
        }

        List<int[]> res = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < list.size(); i++) {
            int end = list.get(i)[0];
            if (start <= end) {
                res.add(new int[] {start, end});
            }
            start = list.get(i)[1];
        }
        if (start < 2400) {
            res.add(new int[]{start, 2400});
        }
        return res;
    }

    /*
    第三题：是给会议分配房间。已知每个会议的人数、开始时间、结束时间，以及每个房间的容量。
输入：
    会议列表：每个会议有名称、人数、开始时间、结束时间
    房间列表：每个房间有名称、容量。

    输出：
    每个会议安排在哪个房间，格式是“会议名:房间名”
    如果没法都安排，输出"impossible"
    assign the room for each meeting

    [m1]
        [m2]
    assign meeting room for the one which ends early than another

    start time
        assign room
    end time
        release room
    Assume: each meeting start time < end time
    class Pair{
        int time;
        boolean isStart;
        int numPeople;
        String meetingRoom;
        int roomCapacity;
    }

    ______s1________________s2________________e1__________e2___________________
    for each start
        find if there is an available room host the meeting

        how?    -> find the room which capacity > numPeople
                                       smallest
                   rooms sorted by its capacity
                          - add
                          - delete
                          TreeMap<Capacity, List<String>> rooms
   for each end
        release room
        -> which room?
            Map<Pair, Pair> <end, start>
    Time: O(nlogn + n * logm)
    m is number of rooms
    n is the number of meeting
   Time: nlogn + nlogm

 */
    static class Pair {
        int time;
        int  isStartTime; // -1: start time 1: end time
        String meetingName;
        int people;
        String roomName;
        int roomCapacity;
        // finish construct later
        public Pair(int time, int isStartTime, String meetingName, int people) {
            this.time = time;
            this.isStartTime = isStartTime;
            this.meetingName = meetingName;
            this.people = people;
        }
    }
    public static List<String> arrangeMeeting(int[][] meetingTime, int[] numOfPeople, String[] meetings, String[] meetingRooms, int[] meetingRoomCapacity) {
        TreeMap<Integer, List<String>> rooms = constructRoomMap(meetingRooms, meetingRoomCapacity);
        HashMap<Pair, Pair> mapToStart = new HashMap<>();
        List<Pair> timePairs = constructPairs(meetingTime, numOfPeople, meetings, mapToStart);
        Collections.sort(timePairs, (a, b) -> {
           int res = a.time - b.time;
           if (res == 0) {
               res = a.isStartTime == -1 ? -1 : 1; // time is same, end time first
               if (res == 0) {
                   res = a.people - b.people > 0 ? -1 : 1; // both start time and both end time, more people first
               }
           }
           return res;
        });
        List<String> res = new ArrayList<>();
        for (Pair p : timePairs) {
            if (p.isStartTime == 1) {
                // assign room
                Integer roomSize = rooms.ceilingKey(p.people);
                List<String> availableRooms = rooms.get(roomSize);
                if (availableRooms == null) {
                    System.out.println("impossible");
                    return null;
                }
                p.roomName = availableRooms.get(availableRooms.size() - 1);
                p.roomCapacity = roomSize;
                availableRooms.remove(availableRooms.size() - 1);
                if (availableRooms.size() == 0) {
                    rooms.remove(roomSize);
                }
                res.add(p.meetingName + " , " + p.roomName);
            } else {
                Pair start = mapToStart.get(p);
                Integer roomSize = start.roomCapacity;
                rooms.putIfAbsent(roomSize, new ArrayList<>());
                rooms.get(roomSize).add(start.roomName);
            }
        }
        return res;
    }
    public static TreeMap<Integer, List<String>> constructRoomMap(String[] meetingRooms, int[] meetingRoomCapacity) {
        TreeMap<Integer, List<String>> rooms = new TreeMap<>();
        for (int i = 0; i < meetingRooms.length; i++) {
            rooms.putIfAbsent(meetingRoomCapacity[i], new ArrayList<>());
            rooms.get(meetingRoomCapacity[i]).add(meetingRooms[i]);
        }
        return rooms;
    }

    public static List<Pair> constructPairs(int[][] meetingTime, int[] numOfPeople, String[] meetings, HashMap<Pair, Pair> mapToStart) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < meetingTime.length; i++) {
            Pair start = new Pair(meetingTime[i][0], 1, meetings[i], numOfPeople[i]);
            Pair end = new Pair(meetingTime[i][1], -1, meetings[i], numOfPeople[i]);
            list.add(start);
            list.add(end);
            mapToStart.put(end, start);
        }
        return list;
    }


    public static void main(String[] args) {
        int[][] meetings = { { 1300, 1500 }, { 930, 1300 }, { 830, 845 } };
        for (int[] time : Q2(Arrays.asList(meetings))) {
            System.out.println("[" + time[0] + ", " + time[1] + "]");
        }
        System.out.println("-----------------------meeting3--------------------------------");
        int[][] meetingTime = {{ 1300, 1500 }, { 930, 1400 }, { 830, 845 } };
        int[] numOfPeople = {10, 9, 7};
        String[] meetingNames = {"MA", "MB", "MC"};
        String[] meetingRooms = {"R1", "R2", "R3"};
        int[] meetingRoomCapacity = {10,9,7};
        List<String> res = arrangeMeeting(meetingTime, numOfPeople, meetingNames, meetingRooms, meetingRoomCapacity);
        for (String s : res) {
            System.out.println(s);
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 1);
        map.put(3,3);
        System.out.println(map.ceilingEntry(2));
    }
}
