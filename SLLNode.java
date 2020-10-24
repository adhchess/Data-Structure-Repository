package DataStructuresPackage;

/**
 * Single Link List Node class
 */
public class SLLNode<T> {

    private T mData;// data stored in this node
    private SLLNode<T> mNext;// next item in the list

    // default constructor
    public SLLNode() {
        this.mData = null;// set data to null
        this.mNext = null;// set next to null

    }

    // constructor passing data
    public SLLNode(T data) {
        this.mData = data;// set the data to the date
        this.mNext = null;// set next to null

    }
    // GETTERS

    public SLLNode<T> getNext() {
        return mNext;// return next item
    }

    public T getData() {
        return mData;// return the data
    }

    // SETTERS
    public void setNext(SLLNode<T> next) {
        this.mNext = next;// set the next item
    }

    public void setData(T data) {
        this.mData = data;// set the data
    }

}
