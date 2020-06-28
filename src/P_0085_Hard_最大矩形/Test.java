package P_0085_Hard_最大矩形;

public class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};

        int res = s.maximalRectangle(matrix);
        System.out.println(res);
    }
}
