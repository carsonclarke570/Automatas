package com.automata.datastructures;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * A helper data structure that functions as a discrete math set
 * 
 * @author Carson Clarke-Magrab
 *
 * @param <E> Any object
 */
public class DiscreteSet<E extends Comparable<E>> extends AbstractSet<E>{

	private transient HashMap<E, Object> map;
	private static final Object PRESENT = new Object();
	
	/**
	 * Creates a new DiscreteSet
	 */
	public DiscreteSet() {
		map = new HashMap<>();
	}
	
	/**
	 * Clear all elements in the set
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * Add an element to the set
	 * 
	 * @param e Element to be added
	 * @return If the element was added
	 */
	public boolean add(E e) {
		return map.put(e, PRESENT) == null;
	}
	
	/**
	 * Checks if the set contains an element
	 * 
	 * @param o Element to check for
	 * @return Whether the set contains the element
	 */
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	/**
	 * Checks if the set is empty
	 * 
	 * @return If the set is empty or not
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	/**
	 * Removes an object from the set
	 * 
	 * @param o  The object to be removed
	 * @return True if the object was removed else if otherwise
	 */
	public boolean remove(Object o) {
		return map.remove(o) != null;
	}
	
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		return map.size();
	}

	
}
