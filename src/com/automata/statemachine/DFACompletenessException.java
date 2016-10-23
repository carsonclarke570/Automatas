package com.automata.statemachine;

public class DFACompletenessException extends Exception {

	private static final long serialVersionUID = -9156663523788523886L;

	public DFACompletenessException() {
		super("DFA is not complete, check to make sure the start state and final states are in the set of states");
	}
}
