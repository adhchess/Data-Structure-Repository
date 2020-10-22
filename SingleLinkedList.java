package DataStructuresPackage;

/*
 *Single Linked List 
 *Implements LinkedList Interface 
 */
public class SingleLinkedList<T> implements LinkedList<T> {

    private LLNode<T> mHead, mTail;// nodes for the head and tail of the linked list
    private int mLength;// how many elements are in the linked list

    // default Constructor
    public SingleLinkedList() {
        this.mHead = null;// set head to null
        this.mTail = null;// set tail to null
        this.mLength = 0;// set length to 0
    }

    public SingleLinkedList(T data) {
        this.insert(data);
    }

    // insert

    // this method does the heavy lifting, it is this method that is called in all
    // other overloaded versions of insert
    @Override
    public void insert(LLNode<T> newNode, int position) throws Exception {
        // TODO Auto-generated method stub

        if (position > getLength())// if the desired position is outside the bounds of the list
            throw new Exception(
                    "ERROR: Invalid Position: " + position + " REASON:  List not Big Enough. Size:  " + getLength());// throw
                                                                                                                     // an
                                                                                                                     // exception
        else if (position < 0)// if position is negative
            throw new Exception("ERROR: Invalid Position: " + position);// throw an exception

        if (position == 0) {// insert at the start of the list
            newNode.setNext(getHead());// set newNode next node
            setHead(newNode);// set the head to the new node
            if (getLength() == 0)// if the list is empty
                setTail(newNode);// set the tail to new node

            setLength(getLength() + 1);// increment length by 1
        } else if (position < getLength()) {// position is somewhere in the middle of the list
            int count = 0;// set a counter to 0
            LLNode<T> previous = null;// set previous to null
            LLNode<T> next = getHead();// set next to the head of the list

            while (count < position) {// while the count is less than the position
                if (next == null)
                    throw new NullPointerException("ERROR: Ran out of list");
                previous = next;// move previous to the next
                next = previous.getNext();// get the item after previous
                count++;// increment count

            }
            newNode.setNext(next);// set newNode's next to next
            previous.setNext(newNode);// set previous' next to newNode
            setLength(getLength() + 1);// increment size by 1
        } else {// position is at the end of the list
            getTail().setNext(newNode);// set tails' next node to newNode
            setTail(newNode);// set tail to newNode
            getTail().setNext(null);// set tail' next node to null
            setLength(getLength() + 1);// increment length by 1
        }
    }

    // calls insert method creating new node in the process
    @Override
    public void insert(T data, int position) {
        // TODO Auto-generated method stub
        try {
            insert(new LLNode<T>(data), position);// attempt to insert data at given position
        } catch (Exception e) {// catch thrown exception if fails
            System.err.println(e.getMessage());// print fail message
        }
    }

    // default inserts at start of list
    @Override
    public void insert(LLNode<T> newNode) {
        // TODO Auto-generated method stub
        try {
            this.insert(newNode, 0);// attempt to insert data at given position
        } catch (Exception e) {// catch thrown exception if fails
            System.err.println(e.getMessage());// print fail message
        }

    }

    // default inserts at start of list
    @Override
    public void insert(T data) {
        // TODO Auto-generated method stub
        this.insert(data, 0);// insert data
    }

    // delete
    private LLNode<T> delete(int position, LLNode<T> start) throws Exception {
        if (position > getLength() || position < 0)// checks to see if position is valid
            throw new Exception("ERROR: Invalid Node to Delete: " + position);// throws exception if position is not
                                                                              // valid

        if (getLength() == 0)// checks if list is empty
            throw new Exception("ERROR: List is Empty");// throws exception if list is empty

        LLNode<T> deleteNode = null;// set deleteNode to null
        if (position == 0) {// delete item at front of list
            deleteNode = start;// set deleteNode to start
            setHead(deleteNode.getNext());// set the new head
            if (getLength() == 1)// if the list is one item long
                setTail(deleteNode.getNext());// move the tail back
            deleteNode.setNext(null);// delete node points to nothing ready to be deleted
        } else if (position < getLength()) {// item is in the middle of the list
            int count = 0;// counter set to 0
            deleteNode = start;// delete node set to start
            LLNode<T> previous = null, next = deleteNode.getNext();// previous set to null, next set to item after
                                                                   // deleteNode

            while (count < position) {// while the position has not been found
                if (next == null)// if next is null
                    throw new NullPointerException(
                            "ERROR: Unable to Delete Item at Position " + position + " REASON: List Ended");// throw an
                                                                                                            // exception
                // move all pointers up one position
                previous = deleteNode;// previous set to deleteNode
                deleteNode = previous.getNext();// deleteNode set to next node
                next = deleteNode.getNext();// next set to two nodes after previous
                count++;// increment count
            }
            previous.setNext(next);// move previous' next pointer to next
            deleteNode.setNext(null);// set deleteNode's next pointer to null ready to be deleted
        } else {// item is at the end
            deleteNode = getTail();// deleteNode set to the tail
            LLNode<T> previous = start;// previous set to the start

            while (previous.getNext() != deleteNode)// search for deleteNode should be at the end of the list
            {
                if (previous.getNext() == null)// if deleteNode is never found
                    throw new NullPointerException("ERROR: Delete Node Never Found");// throw an exception
                previous = previous.getNext();// move pointer along
            }
            // previous should be 1 node behind tail
            setTail(previous);// move tail back 1 node
            previous.setNext(null);// set previous' next pointer to null
        }

        return deleteNode;// return deleteNode
    }

    @Override
    public T delete(int position) {
        // TODO Auto-generated method stub
        T data = null;// data set to null
        try {// attempt to delete the item at position
            LLNode<T> deleteNode = delete(position, getHead());// find the delete node
            data = deleteNode.getData();// get data from delete node
            deleteNode = null;// delete the node
            setLength(getLength() - 1);// decrement the length by 1
        } catch (Exception e) {// if deleting the node fails
            System.out.println(e.getMessage());// print the error
        }

        return data;// return the data
    }

    // default delete removes the front of the list
    @Override
    public T delete() {
        // TODO Auto-generated method stub
        return delete(0);
    }

    // peek
    public T peek(int position) throws Exception {
        if (position > getLength() || position < 0)// if the position is invalid
            throw new Exception("ERROR: Invalid Position: " + position);// throw an exception
        int count = 0;// set counter
        LLNode<T> node = getHead();// node with data
        while (count < position) {// while count is less than the desired position
            if (node == null)// if node is null
                throw new NullPointerException(
                        "ERROR: Item not found at " + position + " Node " + count + " does not exist");// throw an
                                                                                                       // exception
            node = node.getNext();// get the next node
            count++;// increment count
        }
        return node.getData();// return the data
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

    // GETTERS

    public int getLength() {
        return this.mLength;
    }

    private LLNode<T> getHead() {
        return this.mHead;
    }

    private LLNode<T> getTail() {
        return this.mTail;
    }

    // SETTERS
    private void setHead(LLNode<T> newHead) {
        this.mHead = newHead;
    }

    private void setTail(LLNode<T> newTail) {
        this.mTail = newTail;
    }

    private void setLength(int newLength) {
        this.mLength = newLength;
    }

    // isempty
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return getLength() == 0;
    }

    // TOSTRING
    @Override
    public String toString() {
        String returnString = "[";// start of return string
        LLNode<T> start = getHead();// start at the head
        while (start != null) {// read the entire list
            returnString += " " + start.getData().toString() + " ";// append data to the string
            start = start.getNext();// get the next item
        }
        returnString += "]";// close the string
        return returnString;// return the string
    }
}
