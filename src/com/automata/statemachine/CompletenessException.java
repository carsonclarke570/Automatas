package com.automata.statemachine;

public class CompletenessException extends Exception {

	private static final long serialVersionUID = -9156663523788523886L;

	public CompletenessException() {
		super("Automata is not complete, check to make sure the start state and final states are in the set of states");
	}
}
