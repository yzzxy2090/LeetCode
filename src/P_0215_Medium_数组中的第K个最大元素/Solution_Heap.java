package P_0215_Medium_数组中的第K个最大元素;

/**
 * 数组第k大元素
 * 用一个容量为k的最小堆
 * 初始化堆后遍历数组，如果当前遍历元素大于堆顶元素，则用当前遍历元素替换掉堆顶
 * 然后对内进行调整，是的根节点永远是根、左右孩子中的最小值
 *
 * 遍历完数组后堆顶元素就是第k大元素
 */

public class Solution_Heap {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k) {
            return 0;
        }

        int len = nums.length;
        int[] minHeap = new int[k];

        for(int i=0; i<k; i++) {
            insertMinHeap(minHeap, nums[i], i);
        }

        for(int i=k; i<len; i++) {
            if(nums[i] > minHeap[0]) {
                minHeap[0] = nums[i];
                siftdown(minHeap, 0, k);
            }
        }

        return minHeap[0];
    }

    private void insertMinHeap(int[] minHeap, int num, int cur) {
        minHeap[cur] = num;
        int rootIndex = (cur - 1) / 2;

        while(num < minHeap[rootIndex]) {
            swap(minHeap, cur, rootIndex);
            cur = rootIndex;
            rootIndex = (cur - 1) / 2;
        }
    }

    private void siftdown(int[] minHeap, int cur, int heapSize) {
        int leftChildIndex = cur * 2 + 1;
        int rightChildIndex;
        int minIndex = cur;

        while(cur < heapSize) {
            if(leftChildIndex < heapSize) {
                minIndex = leftChildIndex;
                rightChildIndex = leftChildIndex + 1;
                if(rightChildIndex < heapSize) {
                    minIndex = minHeap[leftChildIndex] < minHeap[rightChildIndex] ? leftChildIndex : rightChildIndex;
                }
            }

            minIndex = minHeap[minIndex] < minHeap[cur] ? minIndex : cur;

            if(minIndex == cur) {
                break;
            }

            swap(minHeap, cur, minIndex);
            cur = minIndex;
            leftChildIndex = cur * 2 + 1;
        }

    }

    private void swap(int[] arr, int i, int j) {
        if(arr[i] == arr[j] || i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
