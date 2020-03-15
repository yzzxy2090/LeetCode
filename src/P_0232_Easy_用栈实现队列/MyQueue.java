package P_0232_Easy_用栈实现队列;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class MyQueue {

    ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
    Stack<Integer> stackPush;//stack1用于将元素push入队
    Stack<Integer> stackPop;//stack2用于将元素pop出队

    public MyQueue() {
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    //当stack2为空时，执行shift将stack1中的元素全部依次压入stack2中
    private void shift() {
        if(stackPop.isEmpty()) {
            while(!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stackPush.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        shift();
        return stackPop.pop();
    }

    /** Get the front element. */
    public int peek() {
        shift();
        return stackPop.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }
}
