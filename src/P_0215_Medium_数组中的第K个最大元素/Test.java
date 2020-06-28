package P_0215_Medium_数组中的第K个最大元素;


public class Test {
    public static void main(String[] args) {
        Solution_Partition so = new Solution_Partition();
        int[] arr = {3,2,3,1,2,4,5,5,6};
        int res = so.findKthLargest(arr, 4);
        System.out.println(res);
    }
}
