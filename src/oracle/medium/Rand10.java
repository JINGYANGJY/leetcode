package oracle.medium;

import java.util.Random;

public class Rand10 {
    /*
    错误的分配方式
    7 * 7 ^1+ 7 * 7^0  = 49 + 7 = 56
     1	2	3	4	5	6	7
     8	9	10	11	12	13	14
    15	16	17	18	19	20	21
    22	23	24	25	26	27	28
    29	30	31	32	33	34	35
    36	37	38	39	40	41	42
    43	44	45	46	47	48	49
    50	51	52	53	54	55	56
    正确的combination方式
    最大的数 6 * 7^1 + 6 = 42
    【0 - 48】共49个数
      [0,6] * 7^1  + [0,6]
     */
    public int rand10() {
        int res = 48;
        while (res >= 40) {
            int first = rand7() - 1;
            int second = rand7() - 1;
            res = first * 7 + second;
        }
        return res % 10 + 1;
    }

    private int rand7() {
        Random rand = new Random(7);
        return rand.nextInt() + 1;
    }
}
