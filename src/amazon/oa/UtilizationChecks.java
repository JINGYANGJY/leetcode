package amazon.oa;

public class UtilizationChecks {
    /*
    A new Amazon-developed scaling computing system checks the average utilization of
    the computing system every second while it monitors.
    It implements an autoscale policy to add or reduce instances depending on the current load as described below.
    Once an action of adding or reducing the number of instances is performed,
    the system will stop monitoring for 10 seconds. During that time, the number of instances does not change.

    Average utilization < 25%: An action is instantiated to reduce the number of instances by half
    if the number of instances is greater than 1 (take the ceiling if the number is not an integer).
    If the number of instances is 1, take no action.
    25% <= Average utilization <= 60%: Take no action.
    Average utilization > 60%: An action is instantiated to double the number of instances
    if the doubled value does not exceed 2* 10^8. If the number of instances
    exceeds this limit upon doubling, perform no action.
    Given an array of integers that represent the average utilization at each second,
    determine the number of instances at the end of the time frame.

    Example

    instances = 2
    averageUtil = [25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80]
     */

    public int finalInstances(int instances, int[] averageUtil) {
        int count = instances;
        int index = 0;
        while (index < averageUtil.length) {
            if (averageUtil[index] < 25) {
                if (count > 1) {
                    count = count % 2 == 0 ? count / 2 : count / 2 + 1;
                    index += 10;
                }
            } else if (averageUtil[index] > 60) {
                if (count <= 100000000) {
                    count *= 2;
                    index += 10;
                }
            }
            index++;
        }
        return count;
    }
}
