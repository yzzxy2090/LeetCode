package P_0004_Hard_寻找两个有序数组的中位数;

public class Solution_02 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        //如果两数组总长度为偶数，则left为第k小，right为第k+1小，两下标对应数字平均数为中位数，否则left和right都是第k小
        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;

        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        int first = getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, left);
        int second = getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, right);

        return (first + second) * 0.5;
    }

    //二分查找两数组整体的第k小数
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        //剩余的有效数组长度
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        //len1为0，直接返回nums2中剩余的第k小
        if (len1 == 0) return nums2[start2 + k - 1];

        //len2为0，直接返回nums1中剩余的第k小
        if (len2 == 0) {
            return nums1[start1 + k - 1];
        }

        //求两数组整体第1小的数，也就是此时start1和start2指针所指元素的较小的那个
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        /**
         * Math.min(len1, k / 2)防止此时剩余有效数组长度 < (k/2)，如果将指针指向(k/2)就越界了，此时只要将指针指向该数组末尾即可
         *
         * 现在是要找两数组整体第k小个数
         * 因此分配给两个数组，各找当前有效数组前k/2个数
         */
        int i = Math.min(end1, start1 + (k / 2) - 1);
        int j = Math.min(end2, start2 + (k / 2) - 1);

        /**
         * 如果此时nums1的第k/2小的数大于nums2的第k/2小的数
         * 那么整体第k小的数肯定不在nums2的前k/2小的数里，舍去这部分
         *
         * 递归地向下一层比较
         *
         * 由于已经排除了nums2前k/2个数（实际上是nums2的从start2到j的这些数字）
         * 因此此时问题转化为在nums1和nums2剩余有效数组中找第k-(j-start2+1)小的数
         */
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
