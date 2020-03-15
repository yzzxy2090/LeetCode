package P_0121_Easy_买卖股票的最佳时机;

public class Solution_On {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i=0; i<prices.length; i++) {
            //当前股价更低，应该买入
            if(prices[i] < minPrice) {
                minPrice = prices[i];
            }

            //否则看今天卖出的收益是否超过当前maxProfit
            else {
                maxProfit = Math.max(prices[i] - minPrice, maxProfit);
            }
        }

        return maxProfit;
    }
}
