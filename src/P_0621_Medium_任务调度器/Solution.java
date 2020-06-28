package P_0621_Medium_任务调度器;

/**
 * 排序+优先队列
 *
 *
 * 尽早安排出现次数较多的任务
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int leastInterval(char[] tasks, int n) {

        //times数组保存各种任务需要运行的次数
        int[] times = new int[26];

        for(char c : tasks) {
            times[c - 'A']++;
        }

        //初始化一个大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>(26, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        //把待运行的任务数依次加入大根堆
        for(int num : times) {
            if(num > 0) {
                q.offer(num);
            }
        }

        int turns = 0;

        while(!q.isEmpty()) {
            int i = 0;

            //用一个List保存每轮调度完后，各task剩余的需要运行的次数
            List<Integer> temp = new ArrayList<>();

            //以n+1个任务作为每轮调度的单位
            while(i <= n) {
                if(!q.isEmpty()) {
                    //此次调度完还有剩余次数，出队，并将剩余次数加到List中
                    if(q.peek() > 1) {
                        int timeLeft = q.poll() - 1;
                        temp.add(timeLeft);
                    }
                    //否则直接出队
                    else {
                        q.poll();
                    }
                }

                //轮次增长1
                turns++;

                //当队列和List中都空了的时候说明全部调度完成，退出循环
                if(q.isEmpty() && temp.size() == 0) {
                    break;
                }

                i++;
            }

            //调度完一个轮次后，将暂存在List中的剩余任务次数再添加到优先队列中
            for(int timeLeft : temp) {
                q.offer(timeLeft);
            }
        }
        return turns;
    }
}
