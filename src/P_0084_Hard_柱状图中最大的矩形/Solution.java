package P_0084_Hard_柱状图中最大的矩形;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调栈
 * 本题栈中从栈底到栈顶元素值递增
 *
 * 初始时，栈中压入-1
 * 遍历数组，若当前栈顶不是-1并且栈顶元素(是一个下标值)所对应的的高度 > 当前遍历到的柱状图的高度
 *      将此时的遍历指针i记为right右边界
 *      栈顶元素出栈，记录其代表的柱状图高度为curHeight
 *      记录此时栈顶的元素为left左边界
 *      计算此时curHeight所能向左右扩展出的最大矩形的面积 curArea = curHeight * (right - left - 1)
 *      并尝试更新res
 * 否则，将当前遍历到的元素的数组下边i压入栈中
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode/
 */

public class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int left = 0, right = 0, curHeight = 0;
        int curArea = 0;

        int res = 0;

        stack.push(-1);

        for (int i = 0; i < heights.length; ++i) {

            while (stack.peek() != -1 &&  heights[i] < heights[stack.peek()]) {

                right = i;
                curHeight = heights[stack.pop()];
                left = stack.peek();

                curArea = curHeight * (right - left - 1);

                res = Math.max(res, curArea);
            }

            stack.push(i);
        }

        /**
         * 此时说明已遍历完数组，说明此时栈顶元素所对应的柱状图高度 < 该柱状图以右的柱状图高度
         * 因此该柱状图可以往右一直扩展到数组右边界，即 right = heights.length，且right固定为该值
         *
         * 弹出栈顶元素，curHeight = heights[stack.pop()]
         * 此时栈顶元素stack.peek()为弹出元素所对应的柱状图向左能扩展到的左边界（不包含该栈顶元素所对应的柱状图），即left = stack.peek()
         * 当前弹出栈的元素所对应的柱状图所能扩展成的最大矩形面积为curArea = curHeight * (right - left)
         *
         * 尝试更新res
         */
        right = heights.length;
        while (stack.peek() != -1) {
            curHeight = heights[stack.pop()];
            left = stack.peek();

            curArea = curHeight * (right - left - 1);

            res = Math.max(res, curArea);
        }

        return res;
    }
}
