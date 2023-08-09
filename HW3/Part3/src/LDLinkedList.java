package src;
import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A linked list that removes elements with a custom lazy deletion algorithm.
 * @param <E> the type of elements in this list
 */
public class LDLinkedList<E> extends AbstractList<E> implements List<E> {
    /**
     * A node in the linked list.
     * Each node contains a reference to the next node
     * and the previous node as well as the data.
     * @param <E> the type of elements in this node
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
        }


    }

    /**
     * Overrides toString() method to return a string representation of the list.
     * @return a string representation of the list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<E> node = head; node != null; node = node.next) {
            if (node != deletedNode) {
                sb.append(node.data);
                if (node.next != null && node.next != deletedNode)
                    sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    /**
     * A reference to the head of the list.
     */
    private Node<E> head;
    /**
     * A reference to the deleted node.
     */
    private Node<E> deletedNode;
    /**
     * A flag to indicate if a node has been deleted.
     */
    private boolean deletedFlag;
    /**
     * A list iterator for the linked list.
     */
    private int size;

    /**
     * Default constructor.
     */
    public LDLinkedList() {
        head = null;
        deletedNode = null;
        size = 0;
    }
    /**
     * Overrides size() method to return the size of the list.
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Overrides get() method to return the element's data at the specified index.
     * @param index the index of the element to return
     * @return the element's data at the specified index
     */
    @Override
    public E get(int index) {
        return getNode(index).data;
    }
    /**
     * Returns the node at the specified index.
     * @param index the index of the node to return
     * @return the node at the specified index or
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            if (deletedFlag && node == deletedNode)
                node = node.next;
            node = node.next;
        }
        if (deletedFlag && node == deletedNode && node.next != null)
            node = node.next;
        return node;
    }

    /**
     * Overrides add() method to add the specified element to the end of the list.
     * @param element element whose presence in this collection is to be ensured
     * @return true if the element was added to the list
     */
    public boolean add(E element) {
        add(size, element);
        return true;
    }
    /**
     * Overrides add() method to add the specified element at the specified index.
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
        } else {
            Node<E> prev = getNode(index - 1);
            if (deletedFlag && (prev.next == deletedNode)) {
                newNode.next = prev.next.next;
                newNode.prev = prev;
                if (prev.next.next != null) {
                    prev.next.next.prev = newNode;
                }
                prev.next = newNode;
            } else {
                newNode.next = prev.next;
                newNode.prev = prev;
                if (prev.next != null) {
                    prev.next.prev = newNode;
                }
                prev.next = newNode;
            }

        }
        size++;
    }
    /**
     * Overrides remove() method to remove the element at the specified index.
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        Node<E> node = getNode(index);
        if (deletedFlag) {
            if (node.next == deletedNode) {
                if (node.prev == null && deletedNode.next == null)
                    head = null;
                else if (node.prev == null) {
                    head = deletedNode.next;
                    deletedNode.next.prev = null;
                } else if (deletedNode.next == null)
                    node.prev.next = null;
                else {
                    node.prev.next = deletedNode.next;
                    deletedNode.next.prev = node.prev;
                }
            } else if (node.prev == deletedNode) {
                if (node.next == null && deletedNode.prev == null)
                    head = null;
                else if (node.next == null) {
                    deletedNode.prev.next = null;
                } else if (deletedNode.prev == null)
                    head = node.next;
                else {
                    node.next.prev = deletedNode.prev;
                    deletedNode.prev.next = node.next;
                }
            } else {
                if (node.prev == null)
                    head = node.next;
                else if (node.next == null)
                    node.prev.next = null;
                else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                if (deletedNode.prev == null)
                    head = deletedNode.next;
                else if (deletedNode.next == null)
                    deletedNode.prev.next = null;
                else {
                    deletedNode.prev.next = deletedNode.next;
                    deletedNode.next.prev = deletedNode.prev;
                }
            }
            deletedFlag = false;
            deletedNode = null;
        } else {
            deletedFlag = true;
            deletedNode = node;
        }
        size--;
        return node.data;
    }
    /**
     * Overrides remove() method to remove the first occurrence of the specified element.
     * @param data element to be removed from this list, if present
     * @return true if the element was removed from the list
     */
    public boolean remove(Object data) {
        int index = 0;
        for (Node<E> node = head; node != null; node = node.next, index++) {
            if (node.data.equals(data)) {
                remove(index);
                return true;
            }

        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence
     */
    public ListIterator<E> listIterator() {
        return new LDListIterator();
    }

    /**
     * Overrides listIterator() method to return a list iterator of the elements in this list starting at the specified position in the list.
     * @param index index of the first element to be returned from the
     *        list iterator (by a call to {@link ListIterator#next next})
     * @return
     */
    public ListIterator<E> listIterator(int index) {
        return new LDListIterator(index);
    }

    /**
     * Iterator class for LDLinkedList.
     */
    private class LDListIterator implements ListIterator<E> {
        /**
         * Reference to the next node.
         */
        private Node<E> next;
        /**
         * Reference to the last returned node.
         */
        private Node<E> lastReturned;
        /**
         * Index of the next node.
         */
        private int nextIndex;

        /**
         * Default constructor.
         */
        public LDListIterator() {
            this.next = head;
            this.nextIndex = 0;
            this.lastReturned = null;
        }

        /**
         * Constructor with index.
         * @param index index of the starting node
         */
        public LDListIterator(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            this.next = (index >= size || index < 0) ? null : getNode(index);
            this.nextIndex = index;
            this.lastReturned = null;
        }

        /**
         * Overrides hasNext() method to return true if the iteration has next element.
         * @return true if the iteration has next element
         */
        @Override
        public boolean hasNext() {
            if (nextIndex < size)
                return true;
            return false;
        }

        /**
         * Overrides next() method to go to the next node.
         * @return the data of the last returned node
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned.next = next;
            if (deletedFlag && next.next == deletedNode)
                next = next.next.next;
            else
                next = next.next;
            return next.data;
        }

        /**
         * Overrides hasPrevious() method to return true if the iteration has previous element.
         * @return true if the iteration has previous element
         */
        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        /**
         * Overrides previous() method to go to the previous node.
         * @return the data of the last returned node
         */
        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            next = next.prev;
            if (deletedFlag && lastReturned.prev == deletedNode)
                lastReturned = lastReturned.prev.prev;
            else
                lastReturned = lastReturned.prev;
            return lastReturned.data;
        }

        /**
         * Overrides nextIndex() method to return the index of the element that would be returned by a subsequent call to next().
         * @return the index of the element that would be returned by a subsequent call to next
         */
        @Override
        public int nextIndex() {
            return nextIndex;
        }

        /**
         * Overrides previousIndex() method to return the index of the last returned element.
         * @return the index of the last returned element
         */
        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        /**
         * Overrides remove() method to remove the last returned element from the list.
         * @throws IllegalStateException if the last returned element is null
         */
        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (!deletedFlag) {
                deletedFlag = true;
                deletedNode = lastReturned;
            } else {
                if (lastReturned.prev == deletedNode) {
                    if (lastReturned.next == null && deletedNode.prev == null)
                        head = null;
                    else if (lastReturned.next == null) {
                        deletedNode.prev.next = null;
                    } else if (deletedNode.prev == null)
                        head = lastReturned.next;
                    else {
                        lastReturned.next.prev = deletedNode.prev;
                        deletedNode.prev.next = lastReturned.next;
                    }
                } else if (lastReturned.next == deletedNode) {
                    if (lastReturned.prev == null && deletedNode.next == null)
                        head = null;
                    else if (lastReturned.prev == null) {
                        head = deletedNode.next;
                        deletedNode.next.prev = null;
                    } else if (deletedNode.next == null)
                        lastReturned.prev.next = null;
                    else {
                        lastReturned.prev.next = deletedNode.next;
                        deletedNode.next.prev = lastReturned.prev;
                    }
                } else {
                    if (lastReturned.prev == null)
                        head = lastReturned.next;
                    else if (lastReturned.next == null)
                        lastReturned.prev.next = null;
                    else {
                        lastReturned.prev.next = lastReturned.next;
                        lastReturned.next.prev = lastReturned.prev;
                    }
                    if (deletedNode.prev == null)
                        head = deletedNode.next;
                    else if (deletedNode.next == null)
                        deletedNode.prev.next = null;
                    else {
                        deletedNode.prev.next = deletedNode.next;
                        deletedNode.next.prev = deletedNode.prev;
                    }
                }
                deletedFlag = false;
                deletedNode = null;
            }

        }

        /**
         * Overrides set() method to replace the last returned element with the specified element.
         * @param e the element with which to replace the last element returned by
         */
        @Override
        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.data = e;
        }

        /**
         * Overrides add() method to insert the specified element into the list.
         * @param e the element to insert
         */
        @Override
        public void add(E e) {
            Node<E> newNode = new Node<>(e);
            if (next == null) {
                if (lastReturned == null) {
                    head = newNode;
                } else {
                    lastReturned.next = newNode;
                    newNode.prev = lastReturned;
                }
            } else {
                if (lastReturned == null) {
                    newNode.next = next;
                    next.prev = newNode;
                    head = newNode;
                } else {
                    newNode.next = next;
                    newNode.prev = lastReturned;
                    lastReturned.next = newNode;
                    next.prev = newNode;
                }
            }
            nextIndex++;
            lastReturned = null;

        }
    }
}
