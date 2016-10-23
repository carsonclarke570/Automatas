package com.automata.statemachine;

public class FACompletenessException extends Exception {

	private static final long serialVersionUID = -9156663523788523886L;

	public FACompletenessException() {
		super("FA is not complete, check to make sure the start state and final states are in the set of states");
	}
}
