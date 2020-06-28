package P_0406_Medium_根据身高重建队列;

/**
 * 排序：
 *   - 按高度降序排列。
 *   - 在同一高度的人中，按 k 值的升序排列。
 * 逐个地把它们放在输出队列中，索引等于它们的 k 值。
 * 返回输出队列
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people == null || people.length == 0 || people[0] == null || people[0].length == 0) {
            return people;
        }

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return b[0] - a[0];
                }
            }
        });

        List<int[]> output = new LinkedList<>();

        /**
         * p为身高、k的值对，p[0]表示身高，p[1]表示该人前面有几个人，也就是该轮次该值对应该插入链表的位置的下标
         * 如p={7, 1}
         * 那么此时该p就应该插入到链表下标为1的地方
         */
        for(int[] p : people) {
            output.add(p[1], p);
        }

        //将链表再转为二维数组输出
        return output.toArray(new int[people.length][2]);
    }
}
