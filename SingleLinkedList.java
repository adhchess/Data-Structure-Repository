package DataStructuresPackage;

public class SingleLinkedList<T> {

    class SLLNode {

        private T mData;// data stored in this node
        private SLLNode mNext;// next item in the list

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

        public SLLNode getNext() {
            return mNext;// return next item
        }

        public T getData() {
            return mData;// return the data
        }

        // SETTERS
        public void setNext(SLLNode next) {
            mNext = next;// set the next item
        }

        public void setData(T data) {
            mData = data;// set the data
        }

    }

    private SLLNode mHead;
    private int mLength;

    public SingleLinkedList() {
        this.mHead = null;
        setLength(0);
    }

    public SingleLinkedList(T data) {
        this.mHead = new SLLNode(data);
        setLength(1);
    }

    // INSERT

    // Puts the new node in place given a previous and next pointer
    private void insertNode(SLLNode newNode, SLLNode prev, SLLNode next) {
        newNode.setNext(next);

        if (prev != null)
            prev.setNext(newNode);

        if (isEmpty() || next == getHead())
            setHead(newNode);

        setLength(getLength() + 1);
    }

    // validates and finds the position where the new node should be added
    private void insert(SLLNode newNode, int position) throws Exception {

        if (position > getLength())// if the desired position is outside the bounds of the list
            throw new Exception(
                    "ERROR: Invalid Position: " + position + " REASON:  List is not Big Enough. Size: " + getLength());// throw
                                                                                                                       // an
                                                                                                                       // exception
        else if (position < 0)// if position is negative
            throw new Exception("ERROR: Invalid Position: " + position + " REASON: Position Cannot Be Negative.");// throw
                                                                                                                  // an
                                                                                                                  // exception

        int count = 0;
        SLLNode prev = null;
        SLLNode next = getHead();

        while (count < position) {
            if (next == null)
                throw new NullPointerException("Ran Out of List");
            prev = next;
            next = next.getNext();
            count++;
        }
        insertNode(newNode, prev, next);
    }

    // given the data to be added and the position, attempt to add the data at
    // position
    public void insert(T data, int position) {
        try {
            insert(new SLLNode(data), position);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // default insert add at start
    public void insert(T data) {
        insert(data, 0);
    }

    // DELETE
    // moves the pointer so deleteNode is ready to be deleted
    private void deleteNode(SLLNode deleteNode, SLLNode prev) {
        if (prev != null)
            prev.setNext(deleteNode.getNext());

        if (deleteNode == getHead()) {
            setHead(deleteNode.getNext());
        }
        deleteNode.setNext(null);
        setLength(getLength() - 1);
    }

    // delete function that does the heavy lifting
    // finds the delete node and node before the delete node
    private SLLNode delete(SLLNode start, int position) throws Exception {
        boolean invalidPosition = !(position >= 0 && position < getLength());

        if (isEmpty())
            throw new Exception("List is empty");
        else if (invalidPosition)
            throw new Exception("Invalid Position: " + position);

        SLLNode deleteNode = start;
        SLLNode prev = null;
        int count = 0;

        while (count < position) {
            if (deleteNode == null)
                throw new NullPointerException("List Ends at Position: " + count);
            prev = deleteNode;
            deleteNode = deleteNode.getNext();
            count++;
        }
        deleteNode(deleteNode, prev);

        return deleteNode;
    }

    // delete an item at a given position
    public T delete(int position) {
        T data = null;
        try {
            SLLNode deleteNode = delete(getHead(), position);
            data = deleteNode.getData();
            deleteNode = null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

    // default delete function removes item at position 0
    public T delete() {
        return delete(0);
    }

    public T delete(T searchValue) {
        T data = null;
        int position = search(searchValue);

        if (position != -1)
            data = delete(position);
        return data;
    }

    // HELPER FUNCTIONS

    public boolean isEmpty() {
        return getLength() == 0;
    }

    public T peek(int position) throws Exception {
        T data = null;
        boolean invalidPosition = !(position >= 0 && position < getLength());

        if (isEmpty())
            throw new Exception("List is empty");
        else if (invalidPosition)
            throw new Exception("Invalid Position: " + position);

        SLLNode trav = getHead();
        int count = 0;
        while (count < position) {
            if (trav == null)
                throw new NullPointerException("List Ends at " + count);
            trav = trav.getNext();
            count++;
        }
        data = trav.getData();

        return data;
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
        SLLNode trav = getHead();

        while (trav != null) {
            if (trav.getData().equals(value))
                return count;
            trav = trav.getNext();
            count++;
        }

        return -1;
    }

    // GETTERS
    private SLLNode getHead() {
        return mHead;
    }

    public int getLength() {
        return mLength;
    }

    // SETTERS
    private void setHead(SLLNode newHead) {
        this.mHead = newHead;
    }

    private void setLength(int newLength) {
        this.mLength = newLength;
    }

    // TOSTRING
    @Override
    public String toString() {

        if (isEmpty())
            return "[]";

        String returnString = "[ ";
        SLLNode trav = getHead();
        while (trav != null) {
            returnString += trav.getData() + "=>";
            trav = trav.getNext();
        }
        return returnString + "null ]";
    }
}
