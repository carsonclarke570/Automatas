package com.automata.datastructures;

/**
 * Simple wrapper for a ordered pair object. 
 * 
 * @author Carson Clarke-Magrab
 *
 * @param <E> Type of ordered pair objects
 */
public class OrderedPair<E extends Comparable<E>, F extends Comparable<F>> implements Comparable<OrderedPair<E, F>>{
	
	private E x;
	private F y;
	
	/**
	 * Creates an OrderedPair object.
	 * 
	 * @param x The first element
	 * @param y The second element
	 */
	public OrderedPair(E x, F y) {
		this.x = x;
		this.y = y;
	}
	
	//Getters and Setters
	public E getFirst() {
		return x;
	}
	
	public F getSecond() {
		return y;
	}
	
	public String toString() {
		return "("+ x.toString() +", " + y.toString() + ")";
	}

	@Override
	public int compareTo(OrderedPair<E, F> o) {
		if (getFirst().compareTo(o.getFirst()) != 0 || getSecond().compareTo(o.getSecond()) != 0) {
			return -1;
		}
		return 0;
	}
}
