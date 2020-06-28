package P_0004_Hard_寻找两个有序数组的中位数;

public class Solution_Own {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;

        int first = binSearch(nums1, 0, len1 - 1, nums2, 0, len2 - 1, left);
        int second = binSearch(nums1, 0, len1 - 1, nums2, 0, len2 - 1, right);

        return (first + second) / 2.0;
    }

    private int binSearch(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int range1 = end1 - start1 + 1;
        int range2 = end2 - start2 + 1;

        if(range1 == 0) {
            return nums2[start2 + k - 1];
        }

        if(range2 == 0) {
            return nums1[start1 + k - 1];
        }

        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(range1, (k / 2)) - 1;
        int j = start2 + Math.min(range2, (k / 2)) - 1;

        if(nums1[i] > nums2[j]) {
            return binSearch(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return binSearch(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
