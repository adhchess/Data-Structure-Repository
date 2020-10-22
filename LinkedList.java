package DataStructuresPackage;

/*
 * public interface for linked list class
 * required operations: insert, delete 
 */
public interface LinkedList<T> {
    public abstract void insert(T data, int position);

    public abstract void insert(T data);

    public abstract void insert(LLNode<T> newNode, int position) throws Exception;

    public abstract void insert(LLNode<T> newNode);

    public abstract T delete(int position);

    public abstract T delete();

    public abstract boolean isEmpty();
}
