package DataStructuresPackage;

public class Stack<T> {
    private final SingleLinkedList<T> mStack;

    // CONSTRUCTOR
    public Stack() {
        this.mStack = new SingleLinkedList<T>();
    }

    public Stack(T data) {
        this.mStack = new SingleLinkedList<T>(data);
    }

    // BASIC OPERATIONS
    public void push(T data) {
        mStack.insert(data);
    }

    public T pop() {

        if (isEmpty()) {
            System.err.println("ERROR: Stack Is Empty");
            return null;
        }

        return mStack.delete();
    }

    // HELPER METHODS
    public T peek() {
        if (isEmpty()) {
            System.err.println("ERROR: Stack Is Empty");
            return null;
        }
        return mStack.peek();
    }

    public boolean isEmpty() {
        return mStack.isEmpty();
    }

    // TOSTRING
    public String toString() {
        return mStack.toString();
    }
}
