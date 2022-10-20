package it.unimi.di.sweng;

/**
 * The class Node represents an element that has a value and can be linked both to its predecessor and its follower.
 * @param <T>
 */
public class Node<T> {

    /**
     * Class attributes.
     */
    private final T value;
    private Node<T> next;
    private Node<T> prev;

    /**
     * Class constructor.
     * @param value the value associated to the Node element.
     */
    public Node (T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    /**
     * The method returns the value associated with the Node this.
     * @return the value of type T associated with the Node this.
     */
    public T getValue() {
        return value;
    }

    /**
     * This method gets the follower of the Node this.
     * @return the Node following this.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * This method sets the follower of the Node this.
     * @param next the Node that will follow this.
     */
    public void setNext(Node<T> next) {
        if (next == null) throw new NullPointerException("Argument Node is null.");
        this.next = next;
    }

    /**
     * This method gets the predecessor of the Node this.
     * @return the Node preceding this.
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * This method sets the predecessor of the Node this.
     * @param prev the Node that will precede this.
     */
    public void setPrev(Node<T> prev) {
        if (prev == null) throw new NullPointerException("Argument Node is null.");
        this.prev = prev;
    }
}