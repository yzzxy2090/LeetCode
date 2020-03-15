package P_0122_Easy_买卖股票的最佳时机II;

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }

        int len = prices.length;

        int valley = prices[0];
        int peek = prices[0];
        int maxProfit = 0;

        int i = 0;

        //每轮找到一对波谷和波峰，并且保证了在同一时间点不可能既买入又卖出
        while(i < len-1) {

            //波谷
            while(i < len - 1 && prices[i] >= prices[i+1]) {
                i++;
            }
            valley = prices[i];

            //波峰
            while(i < len - 1 && prices[i] <= prices[i+1]) {
                i++;
            }
            peek = prices[i];

            //把每轮波峰波谷的收益累加到结果中
            maxProfit += peek - valley;
        }
        return maxProfit;
    }
}
