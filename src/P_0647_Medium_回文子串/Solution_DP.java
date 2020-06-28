package P_0647_Medium_回文子串;

public class Solution_DP {
    public int countSubstrings(String s) {
        if(s == null || s == "") {
            return 0;
        }

        char[] cArr = s.toCharArray();
        int n = cArr.length;

        int res = 0;

        boolean[][] isPalin = new boolean[n][n];

        /**
         * j指向当前字符
         * i从j开始往左移
         * 若cArr[j] == cArr[i]，即i和j指向的字符相等，那么在下列两种情况下从字符i到字符j形成一个回文子串
         *      1. i，j指向同一个字符或i，j之间只相隔一个字符，即 (j-i) < 2
         *      2. i，j之间相差两个字符及以上，但他们中间的子字符串是回文子串，即 isPalin[j-1][i+1]
         * 否则i到j形成的子串不是回文串
         */
        for(int j=0; j<n; j++) {
            for(int i=j; i>=0; i--) {
                if(cArr[j] == cArr[i] && ((j-i) < 2 || isPalin[j-1][i+1])) {
                    isPalin[j][i] = true;
                    res++;
                }
            }
        }

        return res;
    }
}
