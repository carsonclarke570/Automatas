package com.automata.datastructures;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A helper data structure that functions as a set
 * 
 * @author Carson Clarke-Magrab
 *
 * @param <E> Any object
 */
public class Set<E extends Comparable<E>> extends AbstractSet<E> implements Comparable<Set<E>>{

	private transient HashMap<E, Object> map;
	private static final Object PRESENT = new Object();
	
	/**
	 * Creates a new Set
	 */
	public Set() {
		map = new HashMap<>();
	}
	
	/**
	 * Creates a new Set from a collection of objects
	 * 
	 * @param c The collection to build the set from
	 */
	public Set(Collection<? extends E> c) {
		this();
		for (E e: c) {
			add(e);
		}
	}
	
	
	/**
	 * Performs the union operation. Doesn't alter state data, creates 
	 * a new instance of a Set
	 * 
	 * @param other The set to perform the union with
	 * @return the newly created set
	 */
	public Set<E> union(Set<E> other) {
		Set<E> newset = new Set<>();
		newset.addAll(this);
		newset.addAll(other);
		return newset;
		
	}
	
	/**
	 * Performs the intersect operation. Doesn't alter state date, creates
	 * a new instance of a Set
	 * 
	 * @param other  The set to intersect with
	 * @return The newly created set
	 */
	public Set<E> intersect(Set<E> other) {
		Set<E> newset = new Set<>();
		Iterator<E> i = iterator();
		while(i.hasNext()) {
			E e = i.next();
			if (other.contains(e)) {
				newset.add(e);
			}
		}
		return newset;
	}
	
	/**
	 * Creates the powerset. Doesn't alter state date, creates
	 * a new instance of a Set
	 * 
	 * @return The powerset of the set
	 */
	public Set<Set<E>> powerSet() {
		Set<Set<E>> ps = new Set<>();
		ps.add(new Set<>());
		
		for (E e: this) {
			Set<Set<E>> newPs = new Set<>();
			
			for (Set<E> subset: ps) {
				newPs.add(subset);
				
				Set<E> newSubset = new Set<>(subset);
				newSubset.add(e);
				newPs.add(newSubset);
			}
			ps = newPs;
		}
		return ps;
	}
	
	/**
	 * Performs the cross product with another set.
	 * 
	 * @param other  The set to perform the action with
	 * @return  The cross product of two sets
	 */
	public <F extends Comparable<F>> Set<OrderedPair<E, F>> cross(Set<F> other) {
		Set<OrderedPair<E, F>> cp = new Set<>();
		for (E e: this) {
			for (F o: other) {
				cp.add(new OrderedPair<E, F>(e, o));
			}
		}
		return cp;
	}
	
	/**
	 * Checks if this set is a subset of another.
	 * 
	 * @param other Set to check if this set is a subset of
	 * @return True if this is a subset of other
	 */
	public boolean isSubsetOf(Set<E> other) {
		for (E e: this) {
			if (!other.contains(e)) {
				return false;
			}
		}
		return true;
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
	
	/**
	 * Returns an iterator over the set
	 */
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	/**
	 * Returns the size of the set
	 */
	public int size() {
		return map.size();
	}

	/**
	 * Compares whether two sets are equal
	 * 
	 * @return -1 if unequal, 0 if equal
	 */
	public int compareTo(Set<E> o) {
		if (!(isSubsetOf(o) && o.isSubsetOf(this))) {
			return -1;
		}
		return 0;
	}

	
}
