package intuit.meetings;

import java.util.*;

/*
第三题：是给会议分配房间。已知每个会议的人数、开始时间、结束时间，以及每个房间的容量。
输入：
    会议列表：每个会议有名称、人数、开始时间、结束时间
    房间列表：每个房间有名称、容量。

    输出：
    每个会议安排在哪个房间，格式是“会议名:房间名”
    如果没法都安排，输出"impossible"

 */
public class MeetingRoom3 {
    /*
    Assume each meeting startTime < endTime
        _s1___s2____s3___e1___e2____e3__
        <startTime, capacity, meeting>
        <endTime, capacity, meeting>
        for each meeting, we want to find its suitable meeting room
        <capacity, room>

        sort startTime pair and endTime pair
        TreeMap<capacity, Set<Room>>
        for each pair
            if startTime
                check from treemap is there is a room which can allow?
                    find ceilingKey of current pair's capacity
                remove from treemap
                if can not find such room, return false
            if endTime
                release room
                add into treemap

        Time: O()
    */

    static class Pair {
        public int time;
        public int meetingcapacity;
        public String meetingName;
        public boolean isStartTime;
        public String roomName;
        public int roomCapacity;
        public Pair(int time, int meetingcapacity, String meetingName, boolean isStartTime) {
            this.time = time;
            this.meetingcapacity = meetingcapacity;
            this.meetingName = meetingName;
            this.isStartTime = isStartTime;
        }

        @Override
        public String toString() {
            return "meetingName='" + meetingName + '\'' +
                    ", roomName='" + roomName + '\'';
        }
    }
    public static List<String> arrangeMeeting(int[][] meetingTime, int[] numOfPeople, String[] meetings, String[] meetingRooms, int[] meetingRoomCapacity) {
        List<Pair> list = new ArrayList<>();
        Map<Pair, Pair> meetingMap = new HashMap<>();
        for (int i = 0; i < meetingTime.length; i++) {
            Pair start = new Pair(meetingTime[i][0], numOfPeople[i], meetings[i], true);
            Pair end = new Pair(meetingTime[i][1], numOfPeople[i], meetings[i], false);
            list.add(start);
            list.add(end);
            meetingMap.put(end, start);
        }
        Collections.sort(list, (p1, p2) -> {
            int res = Integer.compare(p1.time, p2.time);
            if (res == 0) {
                return Integer.compare(p2.meetingcapacity, p1.meetingcapacity);
            }
            return res;
        });
        TreeMap<Integer, List<String>> rooms = new TreeMap<>();
        for (int i = 0; i < meetingRooms.length; i++) {
            rooms.putIfAbsent(meetingRoomCapacity[i], new ArrayList<>());
            rooms.get(meetingRoomCapacity[i]).add(meetingRooms[i]);
        }
        List<String> res = new ArrayList<>();
        for (Pair p : list) {
            if (p.isStartTime) {
                Integer roomCapacity = rooms.ceilingKey(p.meetingcapacity);
                if (roomCapacity == null) {
                    System.out.println("impossible");
                    return null;
                }
                List<String> remainRoom = rooms.get(roomCapacity);
                p.roomName = remainRoom.get(remainRoom.size() - 1);
                p.roomCapacity = roomCapacity;
                remainRoom.remove(remainRoom.size() - 1);
                if (remainRoom.size() == 0) {
                    rooms.remove(roomCapacity);
                }
             } else {
                rooms.putIfAbsent(p.roomCapacity, new ArrayList<>());
                rooms.get(p.roomCapacity).add(p.roomName);
                Pair start = meetingMap.get(p);
                res.add(start.toString());
            }
        }
        return res;
    }

    public static  void main(String[] args) {
        int[][] meetingTime = {{ 1300, 1500 }, { 930, 1400 }, { 830, 845 } };
        int[] numOfPeople = {10, 9, 7};
        String[] meetings = {"MA", "MB", "MC"};
        String[] meetingRooms = {"R1", "R2", "R3"};
        int[] meetingRoomCapacity = {10,9,7};
        List<String> res = arrangeMeeting(meetingTime, numOfPeople, meetings, meetingRooms, meetingRoomCapacity);
        for (String s : res) {
            System.out.println(s);
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 1);
        map.put(3,3);
        System.out.println(map.ceilingEntry(2));
    }
//    MC : R3
//    MB : R2
//    MA : R1






}
