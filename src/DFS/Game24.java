package DFS;

import java.util.Arrays;

public class Game24 {
        public static boolean judgePoint24(int[] nums) {
        /*
         * / + -
         ( )
         BF:
         operands sequence
         --> permutation of the nums
            DFS

         add operator
         add parenthesis
         for each operands sequence

            generate all possible expression
            --> DFS
            level == 4
            at three operators for each level
            four choices + - * /

                        4
                /   |       |   \
                +   -       *    /
            --> expressions
            --> () add paren
            --> more expressions
            --> calcuate if == 24?

          (8-4) * (7-1)
          --> optimizations
          [4, 1, 8, 7] --> (8-4) * (7-1) = 24
               |
             4 7 1
               |
              4 6
               |
              24
              each step:
                choose any two numbers, do four operations
                      |
                      .... just one == 24?


         DFS
         --- 3 level
             for each level, choose 2 number from the array,
             do (-+/*) four branches
         ---Time: O(C4(2) * 4 + C3(2) * 4 + c2(2)*4)

        */
            boolean[] res = new boolean[1];
            dfs(nums, 3, 0, 24, res);
            return res[0];
        }

        private static void dfs(int[] nums, int level, int ans, int target, boolean[] res) {
            if (level == 0 && ans == target) {
                res[0] = true;
                return;
            }
            if (level <= 0) {
                return;
            }
            if (nums[0]== 6 && nums[1] == 4) {
                System.out.println(Arrays.toString(nums));
            }
            for (int i = 0; i <= level; i++) {
                swap(nums, 0, i);
                for (int j = i + 1; j <= level; j++) {
                    swap(nums, j, level);
                    int rightOperand = nums[0];
                    int leftOperand = nums[level];
                    int ori = nums[0];
                    for (int op = 0; op < 4; op++) {
                        if (op == 0) nums[0] = rightOperand + leftOperand;
                        else if (op == 1) nums[0] = rightOperand - leftOperand;
                        else if (op == 3) nums[0] = rightOperand * leftOperand;
                        else if (op == 4 && leftOperand != 0) nums[0] = rightOperand / leftOperand;
                        if (level == 1) ans = nums[0];
                        dfs(nums, level - 1, ans, target, res);
                    }
                    nums[0] = ori;
                    swap(nums, j, level);
                }
                swap(nums,0, i);
            }
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        public static void main(String[] args) {
            int[] arr = {1, 4, 6, 1};
            System.out.println(judgePoint24(arr));
        }
}
