package com.automata.statemachine;

import java.util.HashMap;

import com.automata.datastructures.DiscreteSet;
import com.automata.datastructures.OrderedPair;

/**
 * An class capable of testing and running DFAs. It is necessary to
 * extend this class in order to test your DFA as you must implement the
 * transition function. 
 * 
 * @author Carson Clarke-Magrab
 *
 */
public abstract class DFA {

	private DiscreteSet<Integer> states, finalstates;
	private DiscreteSet<Character> alphabet;
	private String startState;
	
	/**
	 * Constructs a DFA.
	 * 
	 * @param states All the states in the machine, states are numbered
	 * @param alphabet The alphabet that the DFA runs over
	 * @param startState The first state that the DFA starts in. Must be
	 * in the set of states.
	 * @param finalStates The set of accept states. Must be a subset of of states
	 */
	public DFA(DiscreteSet<Integer> states, DiscreteSet<Character> alphabet, String startState, DiscreteSet<Integer> finalStates) {
		this.states = states;
		this.alphabet = alphabet;
		this.startState = startState;
		this.finalstates = finalStates;
	}
	
	/**
	 * Defines the transition from state to state. It should handle
	 * |states| x |alphabet| possible combinations of states and 
	 * characters.
	 * 
	 * @param q The current state to transition from
	 * @param a The character to read
	 * @return The transitioned state number
	 */
	public abstract int transition(int q, char a);
	
	/**
	 * Creates a transition table from the transition function
	 * 
	 * @return A transition table
	 */
	public HashMap<OrderedPair<Integer, Character>, Integer> transitionTable() {
		HashMap<OrderedPair<Integer, Character>, Integer> res = new HashMap<>();
		for (int s: states) {
			for (char c: alphabet) {
				res.put(new OrderedPair<Integer, Character>(s, c), transition(s, c));
			}
		}
		return res;
	}
}
