package amazon.Math;

public class Leet172_FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
       /*
       *********000000000
        # of 0 in the trailing = # of 5 in the trailing
        actually, # of 5 <= # of 2
       */
        int res = 0;
        long currentMultiple = 5;
        while (n >= currentMultiple) {
            res += (n / currentMultiple);
            currentMultiple *= 5;
        }
        return res;
    }
}
