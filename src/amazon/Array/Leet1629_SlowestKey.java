package amazon.Array;

public class Leet1629_SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        /*
        key which had the longest duration
        -> get each key's dureation
           maintain longest
           if current duration > longest
                update
        */

        char[] arr = keysPressed.toCharArray();
        int longest = releaseTimes[0];
        char res = arr[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            int duration = releaseTimes[i] - releaseTimes[i - 1];
            if (duration > longest || duration == longest && arr[i] > res) {
                longest = duration;
                res = arr[i];
            }
        }
        return res;
    }
}
