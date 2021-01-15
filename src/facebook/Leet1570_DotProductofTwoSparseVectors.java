package facebook;

import java.util.*;

public class Leet1570_DotProductofTwoSparseVectors {
    class SparseVector {
        int size;
        List<int[]> pairs;
        SparseVector(int[] nums) {
            this.size = nums.length;
            pairs = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    pairs.add(new int[]{i, nums[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int res = 0;
            int p = 0;
            int q = 0;
            if (size != vec.size) return 0;
            List<int[]> anotherPairs  = vec.pairs;
            while (p < pairs.size() && q < anotherPairs.size()) {
                int index1 = pairs.get(p)[0];
                int index2 = anotherPairs.get(q)[0];
                if (index1 == index2) {
                    res += pairs.get(p)[1] * anotherPairs.get(q)[1];
                    p++;
                    q++;
                } else if (index1 < index2) {
                    p++;
                } else {
                    q++;
                }
            }
            return res;
        }

        public int[] addition(SparseVector vec) {
            int[] res = new int[size];
            int p = 0;
            int q = 0;
            if (size != vec.size) {
                try {
                    throw new Exception("illegal input");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<int[]> anotherPairs  = vec.pairs;
            while (p < pairs.size() && q < anotherPairs.size()) {
                int index1 = pairs.get(p)[0];
                int index2 = anotherPairs.get(q)[0];
                if (index1 == index2) {
                    res[index1] = pairs.get(p)[1] + anotherPairs.get(q)[1];
                    p++;
                    q++;
                } else if (index1 < index2) {
                    res[index1] = pairs.get(p)[1];
                    p++;
                } else {
                    res[index2] = pairs.get(q)[1];
                    q++;
                }
            }
            return res;
        }
    }

    static class SparseVector2 {
        Map<Integer, Integer> map;
        int size;
        public SparseVector2(int size) {
            this.size = size;
            map = new HashMap<>();
        }

        public void set(int key, int value) {
            if (map.containsKey(key)) {
                throw new IllegalArgumentException("key has already existed");
            }
            map.put(key, value);
        }

        public int get(int key) {
            if (key >= size) {
                throw new IllegalArgumentException("key not existed");
            }
            if (map.containsKey(key)) {
                return map.get(key);
            }
            return 0;
        }

        public int dot(SparseVector2 another) {
            if (size != another.size) {
                throw new IllegalArgumentException("invalid input");
            }
            int res = 0;
            for (Integer key : map.keySet()) {
                res += this.get(key) * another.get(key);
            }
            return res;
        }

        public int[] addition(SparseVector2 another) {
            if (size != another.size) {
                throw new IllegalArgumentException("invalid input");
            }
            int[] res = new int[size];
            for (int i = 0; i < size; i++) {
                res[i] = this.get(i) + another.get(i);
            }
            return res;
        }

        public double cosine(SparseVector2 another) {
            return (double) (this.dot(another) / (this.norm() * another.norm()));
        }

        private double norm() {
            double sum = 0;
            for (Integer key : map.keySet()) {
                sum += this.get(key) * this.get(key);
            }
            return Math.sqrt(sum);
        }


    }

    public static void main(String[] args) {
        SparseVector2 v1 = new SparseVector2(5);
        v1.set(0, 4);
        v1.set(1, 5);
        SparseVector2 v2 = new SparseVector2(5);
        v2.set(1, 2);
        v2.set(3, 3);
        System.out.println(Arrays.toString(v1.addition(v2)));
        System.out.println(v1.dot(v2));
        System.out.println(v1.cosine(v2));
    }
}

