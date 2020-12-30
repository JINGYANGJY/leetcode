package oa.tiktok;

public class PRG {
    public static void main(String[] args) {
        System.out.println(game(2, 1, 3));
    }
    public static double game(int n, int m, int k) {
        int[][] mem = new int[n + 1][k + 1];
        int win = find(k,m,n,mem);
        double total = Math.pow(m + 1,k);
        return win / total;
    }
    private static int find(int k, int m, int blood, int[][] mem) {
        if (blood == 0) {
            return 1;
        }
        if (blood < 0 || k == 0) {
            return 0;
        }
        if (mem[blood][k] != 0) {
            return mem[blood][k];
        }

        for (int i = 0; i <= m; i++) {
            mem[blood][k] += find(k-1,m,blood-i,mem);
        }
        return mem[blood][k];
    }
}
