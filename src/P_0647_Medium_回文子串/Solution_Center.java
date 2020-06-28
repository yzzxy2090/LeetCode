package P_0647_Medium_回文子串;

public class Solution_Center {
    public int countSubstrings(String s) {
        if(s == null || s == "") {
            return 0;
        }

        char[] cArr = s.toCharArray();
        int n = cArr.length;

        int res = 0;

        for(int i=0; i<n; i++) {
            //奇数
            res += centerPalindrome(cArr, i, i);
            //偶数
            res += centerPalindrome(cArr, i, i + 1);
        }

        return res;
    }

    /**
     * 中心扩散
     * 如果 left 和 right都没有越界 并且 arr[left] == arr[right]
     * 那么res++，然后left向左扩散一位，right向右扩散一位
     *
     * 这里如果传进来的参数 left==right 说明扩散中心为单中心，考察的是子串为奇数的情况
     * 这里如果传进来的参数 left!=right 说明扩散中心为双中心，考察的是子串为偶数的情况
     */
    private int centerPalindrome(char[] arr, int left, int right) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        int res = 0;

        while(left >= 0 && right < arr.length && arr[left] == arr[right]) {
            res++;
            left--;
            right++;
        }

        return res;
    }
}
