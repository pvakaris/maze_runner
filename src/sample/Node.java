package sample;

public class Node<E> extends javafx.scene.Node {

    private Node next;
    private Node previous;
    private E element;

    public Node(E element, Node<E> next, Node<E> previous) {
        this.element = element;
        this.next = next;
        this.previous = previous;
    }
    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
        this.previous = null;
    }
    public Node() {
        this.element = null;
        this.next = null;
        this.previous = null;
    }


    //
    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    //
    public void setNext(Node<E> newNext) {
        next = newNext;
    }

    public void setPrevious(Node<E> newPrevious) {
        next = newPrevious;
    }

    public void setElement(E newElement) {
        element = newElement;
    }

    @Override
    public javafx.scene.Node getStyleableNode() {
        return null;
    }
}
