package P_0215_Medium_数组中的第K个最大元素;

public class Solution_Partition {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k) {
            return 0;
        }

        int target = nums.length - k + 1;

        return getTopK(nums, 0, nums.length-1, target);
    }

    private int getTopK(int[] arr, int start, int end, int k) {
        if(start == end) {
            return arr[start];
        }

        //swap(arr, start, (int) (end - (Math.random() * (end - start + 1))));

        int index = partition(arr, start, end);

        if(index == k-1) {
            return arr[index];
        }else if(index < k-1) {
            return getTopK(arr, index + 1, end, k);
        } else {
            return getTopK(arr, start, index - 1, k);
        }
    }

    private int partition(int[] arr, int start, int end) {
        if(start == end) {
            return start;
        }

        int pivot = arr[start];

        int low = start;
        int high = end;

        while(low < high) {
            while(low < high && arr[high] > pivot) {
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

    private void swap(int[] arr, int low, int high) {
        if(low == high) {
            return;
        }

        arr[low] = arr[low] ^ arr[high];
        arr[high] = arr[low] ^ arr[high];
        arr[low] = arr[low] ^ arr[high];
    }
}
