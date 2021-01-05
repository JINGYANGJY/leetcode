package DP;

public class Leet552_StudentAttendanceRecordII {
    long MOD = (long)(Math.pow(10, 9) + 7);
    public int checkRecord(int n) {
        /*
        int[] DPA = new int[n];
        */
        long[] DPA = new long[n];
        long[] DPL = new long[n];
        long[] DPP = new long[n];
        long[] DPNAP = new long[n];
        long[] DPNAL = new long[n];
        DPA[0] = 1;
        DPL[0] = 1;
        DPP[0] = 1;
        DPNAP[0] = 1;
        DPNAL[0] = 1;
        for (int i = 1; i < n; i++) {
            DPA[i] = (DPNAP[i - 1] + DPNAL[i - 1]) % MOD;
            DPL[i] = (((DPA[i - 1] + DPP[i - 1]) % MOD + ((i-2) >= 0 ? DPP[i - 2] : DPL[i - 1])) % MOD
                    + ((i-2) >= 0 ? DPA[i - 2] : 0)) % MOD;
            DPP[i] = (DPA[i - 1] + DPL[i -1] + DPP[i - 1]) % MOD;
            DPNAP[i] = (DPNAL[i - 1] + DPNAP[i - 1]) % MOD;
            DPNAL[i] = (DPNAP[i - 1] + ((i-2) >= 0 ? DPNAP[i - 2] : DPNAL[i - 1])) % MOD;
        }
        return (int)(((DPA[n - 1] + DPP[n - 1]) % MOD + DPL[n - 1]) % MOD);
    }
}
