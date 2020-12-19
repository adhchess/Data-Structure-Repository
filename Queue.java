package DataStructuresPackage;

public class Queue<T> {
    private DoubleLinkedList<T> mQueue;

    // CONSTRUCTOR
    public Queue() {
        this.mQueue = new DoubleLinkedList<T>();
    }

    public Queue(T data) {
        this.mQueue = new DoubleLinkedList<T>(data);
    }

    // BASIC OPERATIONS
    public void enQueue(T data) {
        try {
            mQueue.insert(data, getLength());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public T deQueue() {
        if (isEmpty()) {
            System.err.println("ERROR: Queue Is Empty");
            return null;
        }
        return mQueue.delete();
    }

    // HELPER METHODS
    public boolean isEmpty() {
        return mQueue.isEmpty();
    }

    public T peek(int position) {
        T data = null;

        try {
            data = mQueue.peek(position);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return data;
    }

    public T peek() {
        return peek(0);
    }

    private int getLength() {
        return mQueue.getLength();
    }

    // TOSTRING
    @Override
    public String toString() {
        return mQueue.toString();
    }
}
