package com.automata.datastructures;

/**
 * Simple wrapper for a ordered pair object. 
 * 
 * @author Carson Clarke-Magrab
 *
 * @param <E> Type of ordered pair objects
 */
public class OrderedPair<E, F> {
	
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
}
