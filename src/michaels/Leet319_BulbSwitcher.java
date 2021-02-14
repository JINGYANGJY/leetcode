package michaels;

public class Leet319_BulbSwitcher {
    /*
    int[] onOrOff = new int[n]
    for i = 2

     --------------------------------------
     optimizations:
         1  2   3   4   5   6   7   8   9   10  100
            _       _       _       _        _
                i        i       i       i
         1. each bulb togged for odd times, it will be on
         2. i is switched in round d if and only if d divides i
         for number 10 bulb: 2 5 10
                        8       2 4 8
                        4       2 4
                       100      1 2 4  5 10 20 25 50 100

            if n % i == 0
                n toggled
         3. toggled time appears as a pair
            only the number which is a square number
     */

    public int toggled(int n) {
        int res = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (i * i <= n) {
                res++;
            }
        }
        return res;
    }
}
