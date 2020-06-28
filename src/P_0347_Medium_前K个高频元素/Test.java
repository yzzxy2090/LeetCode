package P_0347_Medium_前K个高频元素;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Solution_Execise so = new Solution_Execise();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> res = so.topKFrequent(nums, k);
        for(Integer num : res) {
            System.out.print(num + " ");
        }
    }
}
