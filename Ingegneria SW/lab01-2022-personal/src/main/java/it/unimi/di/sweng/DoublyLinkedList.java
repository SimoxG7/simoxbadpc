package it.unimi.di.sweng;

import java.lang.IllegalStateException;

/**
 * The class DoublyLinkedList is used to represent a doubly linked list. The
 * elements of such list can be of any type.
 * @param <T> the type of the list wanted.
 */
public class DoublyLinkedList<T> {

    /**
     * Class attributes.
     */
    //The head represents the first element of the list.
    private Node<T> head;
    //The tail represents the last element of the list.
    private Node<T> tail;

    /**
     * Class constructor.
     */
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }

    /**
     * Getter and setter methods.
     */

    /**
     * This method returns the head of the list.
     * @return the head of the list as a Node of type T.
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * This method returns the tail of the list.
     * @return the tail of the list as a Node of type T.
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * This method sets the head of the list to the param head.
     * @param head a Node of type T that will be set as new head of the list.
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }

    /**
     * This method sets the tail of the list to the param tail.
     * @param tail a Node of type T that will be set as new tail of the list.
     */
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    /**
     * This method returns the value of the tail element and removes it from the list.
     * @return the value of the tail element of type T.
     * @throws IllegalStateException when user tries to use this method from an empty list.
     */
    public T pop() throws IllegalStateException{
        if (this.getTail() == null){
            throw new IllegalStateException();
        }
        T value = this.getTail().getValue();
        setTail(this.getTail().getPrev());
        return value;
    }

    /**
     * This method returns the value of the head element and removes it from the list.
     * @return the value of the head element of type T.
     * @throws IllegalStateException when user tries to use this method from an empty list.
     */
    public T shift() throws IllegalStateException{
        if (this.getHead() == null){
            throw new IllegalStateException();
        }
        T value = this.getHead().getValue();
        setHead(this.getHead().getNext());
        return value;
    }

    /**
     * This function adds the value parameter to the tail of the list.
     * @param value the value of type T to be inserted in the list.
     */
    public void push(T value){
        Node<T> currentTail = this.getTail();
        Node<T> newNode = new Node<>(value);
        if (currentTail == null){
            this.setHead(newNode);
            this.setTail(newNode);
        } else {
            currentTail.setNext(newNode);
            newNode.setPrev(currentTail);
            this.setTail(newNode);
        }
    }

    /**
     * This function adds the value parameter to the head of the list.
     * @param value the value of type T to be inserted in the list.
     */
    public void unshift(T value){
        Node<T> currentHead = this.getHead();
        Node<T> newNode = new Node<>(value);
        if(currentHead == null){
            this.setHead(newNode);
            this.setTail(newNode);
        } else {
            newNode.setNext(currentHead);
            currentHead.setPrev(newNode);
            this.setHead(newNode);
        }
    }
}