package DataStructuresPackage;

public class DoubleLinkedList<T> {
    class DLLNode {
        private DLLNode mPrevious, mNext;
        private T mData;

        public DLLNode() {
            mPrevious = null;
            mNext = null;
            mData = null;
        }

        public DLLNode(T data) {
            mData = data;
        }

        public T getData() {
            return mData;
        }

        public DLLNode getPrevious() {
            return mPrevious;
        }

        public DLLNode getNext() {
            return mNext;
        }

        public void setData(T data) {
            mData = data;
        }

        public void setNext(DLLNode next) {
            mNext = next;
        }

        public void setPrevious(DLLNode previous) {
            mPrevious = previous;
        }
    }

    private DLLNode mHead, mTail;
    private int mLength;

    public DoubleLinkedList() {
        this.mHead = null;
        this.mTail = null;
        setLength(0);
    }

    public DoubleLinkedList(T data) {
        this.mHead = new DLLNode(data);
        this.mTail = getHead();
        setLength(1);
    }

    // INSERT
    private void insertNode(DLLNode newNode, DLLNode previous, DLLNode next) {
        newNode.setNext(next);
        newNode.setPrevious(previous);

        if (newNode.getPrevious() != null) {
            newNode.getPrevious().setNext(newNode);
        }

        if (newNode.getNext() != null) {
            newNode.getNext().setPrevious(newNode);
        }
        if (isEmpty()) {
            setHead(newNode);
            setTail(newNode);
        }

        if (previous == getTail()) {
            setTail(newNode);
        }

        if (next == getHead()) {
            setHead(newNode);
        }

        setLength(getLength() + 1);
    }

    private void insert(DLLNode newNode, int position) throws Exception {

        if (position > getLength())// if the position is larger than the length of the list
            throw new Exception(
                    "ERROR: Invalid Position: " + position + " REASON: List Is Not Big Enough. Size: " + getLength());// throw
                                                                                                                      // an
                                                                                                                      // exception

        if (position < 0)// if the desired position is negative
            throw new Exception("ERROR: Invalid Position: " + position + " REASON: Position Cannot Be Negative");

        DLLNode prev = null, next = null;
        int count;

        if (position > getLength() / 2) {
            prev = getTail();
            next = null;
            count = getLength();
            while (count > position) {
                if (prev == null)
                    throw new NullPointerException("ERROR: Ran Out Of List From The End At Position " + count);
                next = prev;
                prev = prev.getPrevious();
                count--;
            }
        } else {
            prev = null;
            next = getHead();
            count = 0;
            while (count < position) {
                if (next == null)
                    throw new NullPointerException("ERROR: Ran Out Of List At Position " + count);
                prev = next;
                next = next.getNext();
                count++;
            }
        }
        insertNode(newNode, prev, next);
    }

    public void insert(T data, int position) {
        try {
            insert(new DLLNode(data), position);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void insert(T data) {
        insert(data, 0);
    }

    // DELETE
    private void deleteNode(DLLNode deleteNode) {

        if (deleteNode.getPrevious() != null) {
            deleteNode.getPrevious().setNext(deleteNode.getNext());
        }

        if (deleteNode.getNext() != null) {
            deleteNode.getNext().setPrevious(deleteNode.getPrevious());
        }

        if (deleteNode == getHead()) {
            setHead(deleteNode.getNext());
        }

        if (deleteNode == getTail()) {
            setTail(deleteNode.getPrevious());
        }

        deleteNode.setNext(null);
        deleteNode.setPrevious(null);

        setLength(getLength() - 1);
    }

    private DLLNode delete(DLLNode start, int position) throws Exception {

        if (isEmpty())
            throw new Exception("ERROR: List Is Empty");

        if (position < 0)
            throw new Exception("ERROR: Position Cannot Be Negative: " + position);

        if (position >= getLength())
            throw new Exception("ERROR: Position Must Be Less Than The Size Of The List: " + getLength() + " Position: "
                    + position);

        DLLNode deleteNode = null;
        int count;
        if (position > getLength() / 2) {
            start = getTail();
            count = getLength() - 1;
            while (count > position) {
                if (start == null)
                    throw new NullPointerException("ERROR: List Ends At Position " + count + " From End");
                start = start.getPrevious();
                count--;
            }
        } else {
            start = getHead();
            count = 0;
            while (count < position) {
                if (start == null)
                    throw new NullPointerException("ERROR: List Ends At Position " + count);
                start = start.getNext();
                count++;
            }
        }
        deleteNode = start;
        deleteNode(deleteNode);
        return deleteNode;
    }

    public T delete(int position) {
        T data = null;
        try {
            data = delete(null, position).getData();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

    public T delete() {
        return delete(0);
    }

    public T delete(T searchValue) {

        int position = search(searchValue);
        T data = null;
        if (position != -1) {
            data = delete(position);
        }
        return data;
    }

    // HELPER METHODS
    public boolean isEmpty() {
        return getLength() == 0;
    }

    public T peek(int position) throws Exception {

        if (isEmpty()) {
            throw new Exception("ERROR: List Is Empty");
        }

        if (position < 0)
            throw new Exception("ERROR: Invalid Position: Cannot Be Negative: " + position);

        if (position >= getLength())
            throw new Exception("ERROR: Invalid Posiion: Cannot Be Greater Or Equal To List Length: " + getLength()
                    + " Position: " + position);

        DLLNode trav = getHead();
        int count;

        if (position > getLength() / 2) {
            trav = getTail();
            count = getLength() - 1;
            while (count > position) {
                if (trav == null)
                    throw new NullPointerException("ERROR: List Ends At Position " + count + " From End");
                trav = trav.getPrevious();
                count--;
            }
        } else {
            trav = getHead();
            count = 0;
            while (count < position) {
                if (trav == null)
                    throw new NullPointerException("ERROR: List Ends At Position " + count);
                trav = trav.getNext();
                count++;
            }
        }

        return trav.getData();
    }

    public T peek() {
        T data = null;

        try {
            data = peek(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return data;
    }

    public int search(T value) {
        if (isEmpty())
            return -1;

        int count = 0;
        DLLNode trav = getHead();
        while (trav != null) {
            if (trav.getData() == value)
                return count;
            trav = trav.getNext();
            count++;
        }

        return -1;
    }

    // GETTERS
    public int getLength() {
        return mLength;
    }

    private DLLNode getHead() {
        return mHead;
    }

    private DLLNode getTail() {
        return mTail;
    }

    // SETTERS
    private void setLength(int length) {
        mLength = length;
    }

    private void setHead(DLLNode newHead) {
        mHead = newHead;
    }

    private void setTail(DLLNode newTail) {
        mTail = newTail;
    }

    // TOSTRING
    @Override
    public String toString() {

        if (isEmpty())
            return "[]";
        String returnString = "[ null<=>";
        DLLNode trav = getHead();
        while (trav != null) {
            returnString += trav.getData() + "<=>";
            trav = trav.getNext();
        }
        return returnString + "null ]";
    }
}
