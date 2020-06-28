package P_0347_Medium_前K个高频元素;

import java.util.*;

public class Solution_Execise {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new LinkedList<>();
        if(nums == null || nums.length < k) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        /**
         * 按频次建立小根堆
         * 遍历map的key，加入到小根堆中
         * 若加入当前元素后小根堆size超过k，弹出堆顶元素，也就是当前堆中最小的元素
         * 这样，遍历完成后，堆中留下的元素一定是频次前k大的元素，因为更小的都被弹出去了
         * 即堆顶是频次第k大的元素，堆内是前频次k大的元素
         *
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for(Integer num : map.keySet()) {
            maxHeap.offer(num);

            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        while(!maxHeap.isEmpty()) {
            Integer pollEle = maxHeap.poll();
            res.add(pollEle);
        }

        Collections.reverse(res);

        return res;
    }
}
