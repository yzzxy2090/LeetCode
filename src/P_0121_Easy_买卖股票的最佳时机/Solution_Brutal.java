package P_0121_Easy_买卖股票的最佳时机;

public class Solution_Brutal {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }

        int len = prices.length;

        int res = 0;

        int profit = 0;
        for(int i=1; i<len; i++) {
            for(int j=i-1; j>=0; j--) {
                profit = Math.max(profit, prices[i] - prices[j]);
            }
            res = res > profit ? res : profit;
        }

        return res;
    }
}
