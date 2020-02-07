package P_0128_Hard_最长连续序列;

/**
 * 方法二：哈希表
 *
 * 首先考虑暴力法
 * 因为一个序列可能在 nums 数组的任意一个数字开始，我们可以枚举每个数字作为序列的第一个数字，搜索所有的可能性。
 * 将 nums 数组中的每一个数字，作为序列第一个数字，枚举后面的数字，直到有数字在原数组中没出现过。
 * 当枚举到数组中没有的数字时（即 currentNum 是一个数组中没出现过的数字），记录下序列的长度
 * 如果比当前最优解大的话就更新。算法一定能找到最优解，因为它枚举了每一种可能性。
 *
 *
 * 暴力法时间复杂度高达O(n^3)，虽然时间复杂度高，但思想是由可取之处的，但是需要进行一些优化，才能达到O(n)的时间复杂度。
 *
 * 算法
 *
 * 这个优化算法与暴力算法仅有两处不同：
 * 1.这些数字用一个 HashSet 保存，实现 O(1) 时间的查询
 * 2.同时，我们只把当前数字减1不在哈希表里的数字，作为连续序列的第一个数字去找对应的最长序列，这是因为其他数字一定已经出现在了某个序列里。
 *
 *
 * 时间复杂度：O(n)
 * 尽管在 for 循环中嵌套了一个 while 循环，时间复杂度看起来像是二次方级别的。但其实它是线性的算法。
 * 因为只有当 num 遇到了一个序列的开始， while 循环才会被执行（也就是 num-1 不在数组 nums(集合numSet) 里），
 * while 循环在整个运行过程中只会被迭代 n 次。
 * 这意味着尽管看起来时间复杂度为 O(n⋅n) ，实际这个嵌套循环只会运行 O(n+n)=O(n) 次。
 * 所有的计算都是线性时间的，所以总的时间复杂度是 O(n) 的。
 *
 * 空间复杂度：O(n)
 * 为了实现 O(1) 的查询，我们对哈希表分配线性空间，以保存 nums 数组中的 O(n) 个数字。
 * 除此以外，所需空间与暴力解法一致。
 *
 */

import java.util.HashSet;
import java.util.Set;

public class Solution_Hash {
    public int longestConsecutive(int[] nums) {
        if(nums.length < 2) {
            return nums.length;
        }

        Set<Integer> numSet = new HashSet<Integer>();
        for(int num : nums) {
            numSet.add(num);
        }

        int res = 1;

        /**
         * 遍历集合中的数字
         * 1.若当前遍历到的数字num的前驱数字num-1存在于集合中，那么当前数字num不是序列的第一个数字，跳过当前数字继续往下遍历
         * 2.否则，当前遍历到的数字num为序列第一个数字即左边界，用一个初始为1的int变量len记录该连续序列长度
         *   从当前数字num的后续数字num+1开始往后考察每次循环的num+1是否存在于集合中，是的话则len增长1，num也增长1
         * 然后要更新最长序列长度res
         */
        for(int num : numSet) {
            if(numSet.contains(num-1)) {
                continue;
            }

            int len = 1;
            while(numSet.contains(num+1)) {
                len++;
                num++;
            }

            res = res > len ? res : len;
        }

        return res;
    }
}
