package amazon.Bit;

public class Leet137_SingleNumberII {
    /*
    0^x = x
    x^x = 0
    第一次出现
        first = x
        second = 0
    第二次出现
        first = 0
        second = x
    第三次出现
        first = 0
        second = 0
     */
    int first = 0;
    int second = 0;
    public int singleNumber(int[] nums) {
        for (int n : nums) {
            first = ~second & (first ^ n);
            second = ~first & (second ^ n);
        }
        return first;
    }
}
