package DataStructuresPackage;

public class Stack<T> {
    private final SingleLinkedList<T> stack;

    public Stack() {
        this.stack = new SingleLinkedList<T>();
    }

    public Stack(T data) {
        this.stack = new SingleLinkedList<T>(data);
    }

    public Stack(T[] data) {
        this.stack = new SingleLinkedList<T>();
        for (int i = data.length - 1; i >= 0; i--) {
            push(data[i]);
        }
    }

    public void push(T data) {
        stack.insert(new LLNode<T>(data));
    }

    private T pop(int position) throws Exception {
        if (stack.isEmpty())
            throw new Exception("Stack is empty");
        return stack.delete();
    }

    public T pop() {
        T data = null;

        try {
            data = pop(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

    public T peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
