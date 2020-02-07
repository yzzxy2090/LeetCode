package P_0232_Easy_用栈实现队列;

import java.util.Stack;

public class MyQueue {

    Stack<Integer> stack1;//stack1用于将元素push入队
    Stack<Integer> stack2;//stack2用于将元素pop出队

    public MyQueue() {
        this.stack1 = new Stack<Integer>();
        this.stack2 = new Stack<Integer>();
    }

    //当stack2为空时，执行shift将stack1中的元素全部依次压入stack2中
    private void shift() {
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        shift();
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        shift();
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
