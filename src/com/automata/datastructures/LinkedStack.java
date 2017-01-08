package com.automata.datastructures;

import java.util.Iterator;

/**
 * Implementation of a Stack data structure
 * 
 * @author Carson Clarke-Magrab
 *
 * @param <E> Type of elements
 */
public class LinkedStack<E extends Comparable<E>>{

	private class Node {
		private E data = null;
		private Node next = null;
	}
	
	private Node head;
	private int size;
	
	/**
	 * Constructs a new LinkedStack
	 */
	public LinkedStack() {
		head = null;
		size = 0;
	}

	/**
	 * Pushes a new element to the top of the stack
	 * 
	 * @param e Element to be pushed
	 */
	public void push(E e) {
		Node ins = new Node();
		ins.data = e;
		if (head == null) {
			head = ins;
		} else {
			ins.next = head;
			head = ins;
		}
		size++;
	}
	
	/**
	 * Pops the top off the stack
	 * 
	 * @return The top of the stack or null if empty
	 */
	public E pop() {
		if (head == null) {
			return null;
		} 
		E res = head.data;
		head = head.next;
		size--;
		return res;
	}
	
	/**
	 * Returns if the stack is empty
	 * 
	 * @return If the stack is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Peeks at the top of the stack
	 * 
	 * @return Element at the top of the stack
	 */
	public E peek() {
		return head.data;
	}
	
	/**
	 * Peeks at a given index
	 * 
	 * @param index Index to peek
	 * @return Data at that point
	 */
	public E peek(int index) {
		Node cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.data;
	}
	
	/**
	 * Return the length of the stack
	 * 
	 * @return Length of the stack
	 */
	public int size() {
		return size;
	}
}
