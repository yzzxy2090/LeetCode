package P_0215_Medium_数组中的第K个最大元素;

public class Solution_Partition02 {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k) {
            return 0;
        }

        //找数组中第k大的数 -> 找数组中第len-k+1小的数
        int target = nums.length - k + 1;

        return getTopK(nums, 0, nums.length-1, target);
    }

    //找数组第k小数
    private int getTopK(int[] arr, int start, int end, int k) {
        //搜索的范围已经缩小到一个元素，则该元素就是结果
        if(start == end) {
            return arr[start];
        }

        //随机选取pivot，效率提升很多，这里取arr[start]为pivot，所以将随机选取值交换到start位置上
        swap(arr, start, end - (int)(Math.random() * (end - start + 1)));

        int index = partition(arr, start, end);

        //当前划分之后确定的index就是k-1，说明该arr[index]就是第k小数，直接返回
        if(index == k-1) {
            return arr[index];
        } else if(index < k-1) {
            return getTopK(arr, index + 1, end, k);
        } else {
            return getTopK(arr, start, index - 1, k);
        }
    }

    //partition过程，返回当前轮次确定的pivot的位置，即pivot应该所在的数组下标值
    private int partition(int[] arr, int start, int end) {
        if(start == end) {
            return start;
        }

        int pivot = arr[start];

        int low = start;
        int high = end;

        while(low < high) {
            while(low < high && arr[high] >= pivot) {
                high--;
            }

            while(low < high && arr[low] <= pivot) {
                low++;
            }

            if(low < high) {
                swap(arr, low, high);
            }
        }
        swap(arr, start, low);
        return low;
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
