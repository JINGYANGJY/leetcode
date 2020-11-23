package interview.hard;
/*
Given a file and assume that you can only read the file using a given method read4,
implement a method read to read n characters. Your method read may be called multiple times.

Method read4:
The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf4.
The return value is the number of actual characters read.
Note that read4() has its own file pointer, much like FILE *fp in C.

Method read:
By using the read4 method, implement the method read that reads n characters from the file
and store it in the buffer array buf. Consider that you cannot manipulate the file directly.
The return value is the number of actual characters read.
 */
/*
use method read4 method to implement method read
counter the character read actual read
    buffer: used to store characters by using method read4
    buf: used to store characters by using method read
    step1:
        if buffer is empty
        call read4
        if  still is empty
        break -- it means there are no characters in the file
        iterate characters in buffer to buf
        return actual number of characters read
 */
class Solution /*extends Reader4*/ {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] buffer = new char[4];
    int numOfRead4 = 0;
    int fp = 0;
    public int read(char[] buf, int n) {
        int counter = 0;
        while (counter < n) {
            if (fp == 0) {
                numOfRead4 = read4(buffer);
            }
            if (numOfRead4 == 0) {
                break;
            }
            while (counter < n && fp < numOfRead4) {
                buf[counter++] = buffer[fp++];
            }
            if (fp == numOfRead4) {
                fp = 0;
            }
        }
        return counter;
    }
    public int read4(char[] buffer) {
        return 0;
    }
}
