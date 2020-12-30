package oa.tiktok;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class memoryleak {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int m1 = sc.nextInt();
            int m2 = sc.nextInt();
            List<Integer> res = memoryLeak(m1, m2);
            for (Integer j : res) {
                System.out.print(j);
                System.out.print(' ');
            }
            System.out.println();
       }
    }

    public static List<Integer> memoryLeak(int m, int n) {
        int res = (int)Math.floor(Math.sqrt((double)(Math.abs(m - n)) * 2));
        boolean mB = m > n;
        if (mB) {
            m -= (1 + res) * res /2;
        } else {
            n -= (1 + res) * res /2;
        }
        mB = m >= n;
        if (mB) {
            return getRes(m,n,res + 1,mB);
        } else {
            return getRes(n, m, res + 1, mB);
        }
    }
    public static List<Integer> getRes(int m, int n, int res, boolean mB) {
        int n1 = getNum(1, res - 1, -m);
        int n2 = getNum(1, res, -n);
        m -= res * n1 + n1 * (n1 - 1);
        n -= (res + 1) * n2 + n2 * (n2 - 1);
        res += n1 + n2;
        if (!mB)
            return Arrays.asList(res, n, m);
        else return Arrays.asList(res,m,n);
    }

    public static int getNum(int a, int b, int c) {
        int res=(int)Math.floor((-b+Math.sqrt(b*b-4*a*c))/(2*a));
        return res;
    }
}
