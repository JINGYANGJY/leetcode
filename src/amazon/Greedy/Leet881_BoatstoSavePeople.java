package amazon.Greedy;

import java.util.Arrays;

public class Leet881_BoatstoSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        /*
        sort the people
        goals to get the minimum number of boats to carry every given person
        */
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int curSum = 0;
        int count = 0;
        int res = 0;
        while (left <= right) {
            if (count < 2 && curSum + people[right] <= limit) {
                curSum += people[right];
                count++;
                right--;
            } else if (count < 2 && curSum + people[left] <= limit) {
                curSum += people[left];
                left++;
                count++;
            } else {
                res++;
                count = 0;
                curSum = 0;
            }
        }
        if (curSum > 0) return res + 1;
        return res;
    }
}
