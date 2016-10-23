package com.automata.datastructures;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A helper data structure that functions as a discrete math set
 * 
 * @author Carson Clarke-Magrab
 *
 * @param <E> Any object
 */
public class DiscreteSet<E extends Comparable<E>> extends AbstractSet<E> implements Comparable<DiscreteSet<E>>{

	private transient HashMap<E, Object> map;
	private static final Object PRESENT = new Object();
	
	/**
	 * Creates a new DiscreteSet
	 */
	public DiscreteSet() {
		map = new HashMap<>();
	}
	
	/**
	 * Creates a new DiscreteSet from a collection of objects
	 * 
	 * @param c The collection to build the set from
	 */
	public DiscreteSet(Collection<? extends E> c) {
		this();
		for (E e: c) {
			add(e);
		}
	}
	
	/**
	 * Performs the union operation. Doesn't alter state data, creates 
	 * a new instance of a DiscreteSet
	 * 
	 * @param other The set to perform the union with
	 * @return the newly created set
	 */
	public DiscreteSet<E> union(DiscreteSet<E> other) {
		DiscreteSet<E> newset = new DiscreteSet<>();
		newset.addAll(this);
		newset.addAll(other);
		return newset;
		
	}
	
	/**
	 * Performs the intersect operation. Doesn't alter state date, creates
	 * a new instance of a DiscreteSet
	 * 
	 * @param other  The set to intersect with
	 * @return The newly created set
	 */
	public DiscreteSet<E> intersect(DiscreteSet<E> other) {
		DiscreteSet<E> newset = new DiscreteSet<>();
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
	 * a new instance of a DiscreteSet
	 * 
	 * @return The powerset of the set
	 */
	public DiscreteSet<DiscreteSet<E>> powerSet() {
		DiscreteSet<DiscreteSet<E>> ps = new DiscreteSet<>();
		ps.add(new DiscreteSet<>());
		
		for (E e: this) {
			DiscreteSet<DiscreteSet<E>> newPs = new DiscreteSet<>();
			
			for (DiscreteSet<E> subset: ps) {
				newPs.add(subset);
				
				DiscreteSet<E> newSubset = new DiscreteSet<>(subset);
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
	public <F extends Comparable<F>> DiscreteSet<OrderedPair<E, F>> cross(DiscreteSet<F> other) {
		DiscreteSet<OrderedPair<E, F>> cp = new DiscreteSet<>();
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
	public boolean isSubsetOf(DiscreteSet<E> other) {
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
	
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		return map.size();
	}

	/**
	 * Compares whether two sets are equal
	 * 
	 * @return -1 if unequal, 0 if equal
	 */
	public int compareTo(DiscreteSet<E> o) {
		if (!(isSubsetOf(o) && o.isSubsetOf(this))) {
			return -1;
		}
		return 0;
	}

	
}
