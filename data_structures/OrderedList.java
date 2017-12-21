/**
 * cssc0956
 * 
 * Luke Stodgel
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList <E extends Comparable <E>> implements PriorityQueue<E> {
	
    public static final int DEFAULT_MAX_CAPACITY = 1000;
    private int currentSize;
    Node<E> head;
    
    public OrderedList(){
    	head = null;
    	currentSize = 0;
    }
    
    public class Node<T> {     //should this be private?
		T data;
		Node<T> next;
		
		public Node(T obj) {
			this.data = obj;
			next = null;
		}
	}
    //  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
    public boolean insert(E object) {
    	Node<E> newNode = new Node<E>(object);
    	if (head == null) //empty list
    		head = newNode;
    	Node<E> previous = null, current = head;
    	while(current != null && object.compareTo(current.data) >= 0) {
    		previous = current;
    		current = current.next;
    	}
        if (previous == null) {
    		newNode.next = head;
    		head = newNode;
    	}
    	else {
    		previous.next = newNode;
    		newNode.next = current;
    	}
    	currentSize++;
    	return true;
    }
   
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove() {
    	if(isEmpty()) return null;
    	E tmp = head.data;
    	head = head.next;
    	this.currentSize--;
    	return tmp;
    	}
   
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek() {
    	return head.data;
    }
    
    //  Returns true if the priority queue contains the specified element
    //  false otherwise.
    public boolean contains(E obj) {
    	if(head == null) return false;
    	
    	Node<E> current = head;
    	while (current!= null){
    		if ((current.data).compareTo(obj) == 0) return true;
    		current = current.next;
    	}
    	return false;
    }
   
    //  Returns the number of objects currently in the PQ.
    public int size() {
    	return currentSize;
    }
      
    //  Returns the PQ to an empty state.
    public void clear() {
    	currentSize = 0;
    }
   
    //  Returns true if the PQ is empty, otherwise false
    public boolean isEmpty() {
    	return head == null;
    }
   
    //  Returns true if the PQ is full, otherwise false.  List based
    //  implementations should always return false.
    public boolean isFull() {
    	return false;
    }
    class IteratorHelper implements Iterator<E> {
		Node<E> index;
		
		public IteratorHelper(){
			index = head;
		}
		
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E tmp = index.data;
			index = index.next;
			return tmp;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public boolean hasNext() {
			return (index != null);
		}
	}
    //  Returns an iterator of the objects in the PQ, in no particular
    //  order.  The iterator must be fail-fast.
    public Iterator<E> iterator() {
    	return new IteratorHelper();
    }
}
