package michaels;

public class bit {

    public static int bits(int m) {
        int i = 1;
        int count = 0;
        while (i <= m) {
            if ((m & i) != 0) {
                count++;
            }
            i = i << 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(bits(8));
    }
}
