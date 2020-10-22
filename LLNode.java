package DataStructuresPackage;

/**
 * Link List Node class
 */
public class LLNode<T> {

    private T mData;// data stored in this node
    private LLNode<T> mNext;// next item in the list
    private LLNode<T> mPrevious;// previous item in the list

    // default constructor
    public LLNode() {
        this.mData = null;// set data to null
        this.mNext = null;// set next to null
        this.mPrevious = null;// set previous to null
    }

    // constructor passing data
    public LLNode(T data) {
        this.mData = data;// set the data to the date
        this.mNext = null;// set next to null
        this.mPrevious = null;// set previous to null
    }

    // GETTERS

    public LLNode<T> getNext() {
        return mNext;
    }

    public LLNode<T> getPrevious() {
        return mPrevious;
    }

    public T getData() {
        return mData;
    }

    // SETTERS
    public void setNext(LLNode<T> next) {
        this.mNext = next;
    }

    public void setPrevious(LLNode<T> previous) {
        this.mPrevious = previous;
    }

    public void setData(T data) {
        this.mData = data;
    }

}
