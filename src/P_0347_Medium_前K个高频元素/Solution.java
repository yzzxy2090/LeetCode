package P_0347_Medium_前K个高频元素;

import java.util.*;

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 队首为频次最小的元素
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n: count.keySet()) {
            heap.offer(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> res = new LinkedList();
        while (!heap.isEmpty())
            res.add(heap.poll());
        Collections.reverse(res);
        return res;
    }
}
