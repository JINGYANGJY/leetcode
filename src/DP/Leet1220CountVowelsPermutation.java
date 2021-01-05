package DP;

public class Leet1220CountVowelsPermutation {
    int mod = (int) (Math.pow(10, 9) + 7);
    public int countVowelPermutation(int n) {
        /*
        dpA[i]: ends with i, place A at current position, how many strings
        dpE[i]:                    E
        dpI[i]                     I
        dpO[i]                     O
        dpU[i]                     U
        dpA[i] = dpE[i - 1] + dpU[i - 1]
        dpE[i] = dpA[i - 1]
        dpI[i] = dpE[i - 1] + dpO[i - 1]
        dpO[i] =
        dpU[i] = dpO[i - 1];
        */
        int[] dpA = new int[n  + 1];
        int[] dpE = new int[n  + 1];
        int[] dpI = new int[n  + 1];
        int[] dpO = new int[n  + 1];
        int[] dpU = new int[n  + 1];
        dpA[1] = dpE[1] = dpO[1] = dpI[1] = dpU[1] = 1;
        for (int i = 2; i <= n; i++) {
            dpA[i] = ((dpE[i - 1] + dpU[i - 1]) % mod + dpI[i - 1]) % mod;
            dpE[i] = (dpA[i - 1] + dpI[i - 1]) % mod;
            dpI[i] = (dpE[i - 1] + dpO[i - 1]) % mod;
            dpO[i] = dpI[i - 1];
            dpU[i] = (dpO[i - 1] + dpI[i - 1]) % mod;
        }
        return ((((dpA[n] + dpE[n]) % mod + dpI[n]) % mod + dpO[n]) % mod + dpU[n]) % mod;
    }
}
