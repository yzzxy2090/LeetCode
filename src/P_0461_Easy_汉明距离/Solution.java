package P_0461_Easy_汉明距离;

public class Solution {
    public int hammingDistance(int x, int y) {
        int dis = 0;
        int temp = x ^ y;

        while(temp != 0) {
            if((temp & 1) == 1) {
                dis++;
            }
            temp >>= 1;
        }

        return dis;
    }
}
