package P_0046_Medium_全排列;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Solution_DFS01 s = new Solution_DFS01();
        int[] arr = {1, 2, 3};
        List<List<Integer>> res = s.permute(arr);

        for(List<Integer> temp : res) {
            for(Integer num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
