package intuit.meetings;

/*
类似meeting rooms，输入是一个int[][] meetings, int start, int end, 每个数都是时间，13：00 =》 1300， 9：30 =》 930，
看新的meeting 能不能安排到meetings
ex: {[1300, 1500], [930, 1200],[830, 845]}, 新的meeting[820, 830], return true; [1450, 1500] return false;

goals to check all meetings has no overlap with new meeting
    Time:O(n)
        [m1]   [m2]
        no overlap
            [m1]   [m2]
            or
            [m2]   [m1]


        [ m1  ]
             [ m2 ]
                              [ m2 ]
                                  [ m1 ]
         m1[1] <= m2[0] || m2[1] <= m1[0]
         -> m1[1] > m2[0] && m2[1] > m1[0]


 */
public class MeetingRoom1 {
    public static boolean canArrange(int[][] meetings, int start, int end) {
        for (int[] meeting : meetings) {
            if (meeting[0] < end && meeting[1] > start) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] meetings = { { 1300, 1500 }, { 930, 1200 }, { 830, 845 } };
        System.out.println(canArrange(meetings, 820, 830));
    }
}
