package P_0378_Medium_有序矩阵中第K小的元素;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 方法二：大根堆
 *
 * 这道题让我们求有序矩阵中第K小的元素
 * 这道题的难点在于数组并不是蛇形有序的
 * 意思是当前行的最后一个元素并不一定会小于下一行的首元素，所以我们并不能直接定位第K小的元素
 * 所以只能另辟蹊径。先来看一种利用堆的方法
 * 我们使用一个最大堆，然后遍历数组每一个元素，将其加入堆
 * 根据最大堆的性质，大的元素会排到最前面
 * 然后我们看当前堆中的元素个数是否大于k，大于的话就将首元素去掉
 * 循环结束后我们返回堆中的首元素即为所求
 *
 * 时间复杂度O(n^2*logn)
 *
 */

public class Solution_MinHeap {
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }

        //PriorityQueue默认是小根堆，将其改为大根堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                queue.offer(matrix[i][j]);
                if(queue.size() > k) {
                    queue.poll();
                }
            }
        }
        return queue.peek();
    }
}
