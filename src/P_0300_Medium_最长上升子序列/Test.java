package P_0300_Medium_最长上升子序列;

public class Test {
    public static void main(String[] args) {
        Solution_GreedyAndBinSearch so = new Solution_GreedyAndBinSearch();
        int[] arr = {10,9,2,5,3,4};

        int res = so.lengthOfLIS(arr);
        System.out.println(res);
    }
}
