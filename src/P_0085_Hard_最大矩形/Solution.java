package P_0085_Hard_最大矩形;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int res = 0;
        int[] heights = new int[matrix[0].length];

        /**
         * 记录以每行为底座的'1'的柱状图
         * 转化为求以每层为底的柱状图形成的最大矩形面积，然后对每层的最大矩形求最大值
         *
         * 对于当前i层的柱状图heights
         *      如果 matrix[i][j] == '0'，heights[j] = 0
         *      如果 matrix[i][j] == '1'，heights[j] += 1
         */
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j]+1;
            }
            res = Math.max(res, maxArea(heights));
        }

        return res;
    }

    //求柱状图中最大矩形的逻辑
    private int maxArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int right = 0, left = 0, curHeight = 0, curArea = 0, res = 0;

        stack.push(-1);

        for(int i=0; i<heights.length; ++i) {
            while(stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                right = i;
                curHeight = heights[stack.pop()];
                left = stack.peek();

                curArea = curHeight * (right - left - 1);
                res = Math.max(res, curArea);
            }

            stack.push(i);
        }

        right = heights.length;
        while(stack.peek() != -1) {
            curHeight = heights[stack.pop()];
            left = stack.peek();
            curArea = curHeight * (right - left - 1);

            res = Math.max(res, curArea);
        }

        return res;
    }
}
