package P_0005_Medium_最长回文子串;

public class Solution_Center {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        char[] cArr = s.toCharArray();
        int n = cArr.length;

        int maxLen = 0;
        int start = 0, end = 0;

        int lenOdd, lenEven;
        int tempLen = 0;

        for(int i=0; i<n; i++) {
            lenOdd = isPalin(cArr, i, i);
            lenEven = isPalin(cArr, i, i+1);

            tempLen = Math.max(lenOdd, lenEven);

            if(tempLen > (end - start)) {
                start = i - (tempLen - 1) / 2;
                end = i + tempLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int isPalin(char[] arr, int left, int right) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        while(left >= 0 && right < arr.length && arr[left] == arr[right]) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
