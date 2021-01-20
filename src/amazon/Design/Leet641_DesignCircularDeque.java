package amazon.Design;

public class Leet641_DesignCircularDeque {
    class MyCircularDeque {
    /*
    set size int[]
    fron = -1
    end = -1

    size
          front < end
          size = end - front  + 1
          front > end
          size = end + 1 + k - 1 - front + 1 == end + 1 + k - front
    empty:
        front == -1 && end == -1

    */
        /** Initialize your data structure here. Set the size of the deque to be k. */
        int[] array;
        int front;
        int end;
        int capacity;
        public MyCircularDeque(int k) {
            this.array = new int[k];
            this.front = -1;
            this.end = -1;
            this.capacity = k;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (this.isFull()) {
                return false;
            }
            if (isEmpty()) {
                front = end = 0;
                this.array[front] = value;
            } else {
                front = front == 0 ? capacity - 1 : front - 1;
                this.array[front] = value;
            }
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (this.isFull()) {
                return false;
            }
            if (isEmpty()) {
                front = end = 0;
                this.array[end] = value;
            } else {
                end = end == capacity - 1 ? 0 : end + 1;
                this.array[end] = value;
            }
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (this.isEmpty()) {
                return false;
            }
            if (front == end) {
                front = end = -1;
            } else {
                front = front == capacity - 1 ? 0 : front + 1;
            }
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (this.isEmpty()) {
                return false;
            }
            if (front == end) {
                front = end = -1;
            } else {
                end = end == 0 ? capacity - 1 : end - 1;
            }
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if (this.isEmpty()) {
                return -1;
            }
            return this.array[front];
        }


        /** Get the last item from the deque. */
        public int getRear() {
            if (this.isEmpty()) {
                return -1;
            }
            return this.array[end];
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return front == -1 && end == -1;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            if (!this.isEmpty()) {
                if (front <= end) {
                    return end - front + 1 == capacity;
                } else {
                    return capacity - front + end + 1 == capacity;
                }
            }
            return false;
        }
    }
}
