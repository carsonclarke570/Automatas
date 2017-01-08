package com.automata.statemachine;

import java.util.HashMap;

import com.automata.datastructures.Set;
import com.automata.datastructures.OrderedPair;

/**
 * An class capable of testing and running DFAs. It is necessary to
 * extend this class in order to test your DFA as you must implement the
 * transition function. 
 * 
 * @author Carson Clarke-Magrab
 *
 */
public abstract class NFA {

	private Set<Integer> states, finalstates;
	private Set<Character> alphabet;
	private int startState;
	
	/**
	 * Constructs a NFA.
	 * 
	 * @param states All the states in the machine, states are numbered
	 * @param alphabet The alphabet that the NFA runs over
	 * @param startState The first state that the NFA starts in. Must be
	 * in the set of states.
	 * @param finalStates The set of accept states. Must be a subset of of states
	 */
	public NFA(Set<Integer> states, Set<Character> alphabet, int startState, Set<Integer> finalStates) throws FACompletenessException {
		this.states = states;
		this.alphabet = alphabet;
		this.startState = startState;
		this.finalstates = finalStates;
		if (!ensureCompleteness()) {
			throw new FACompletenessException();
		}
	}
	
	/**
	 * Reads a string and then returns true if it accepts or false
	 * if it rejects
	 * 
	 * @param s The String to run
	 * @return True if the string accepts false if otherwise
	 * @throws DFACompletenessException If the DFA being run is incomplete
	 */
	public boolean run(String s) {
		Set<Integer> currentStates = new Set<Integer>() ;
		currentStates.add(startState);
		Set<Integer> eStates = new Set<Integer>() ;
		for (int i: currentStates) {
			Set<Integer> temp = eMove(i);
			eStates = eStates.union(temp);
		}
		currentStates = eStates;
		
		for(int i = 0; i < s.length(); i++) {
			Set<Integer> nextStates = new Set<Integer>();
			Set<Integer> emptyStates = new Set<Integer>();
			for (int j: currentStates) {
				Set<Integer> temp = eMove(j);
				emptyStates = emptyStates.union(temp);
			}
			for (int j: emptyStates) {
				Set<Integer> temp = transition(j, s.charAt(i));
				if (temp != null) {
					nextStates = nextStates.union(temp);
				}
			}
			currentStates = nextStates;
		}
		return currentStates.intersect(finalstates).size() > 0;
	}
	
	/**
	 * Defines the transition from state to state. It is not necessary for
	 * states to have a transition for each character in the alphabet. If 
	 * there are no transitions for a given state and character, return null.
	 * 
	 * @param q The current state to transition from
	 * @param a The character to read
	 * @return The set of transitioned state numbers or null if there is no 
	 * transition for a given character 
	 */
	public abstract Set<Integer> transition(int q, char a);
	
	/**
	 * Defines the empty string transition of each state. In other words, the
	 * function returns the set of states reachable by moving along empty string
	 * transitions. This includes the inputted state.
	 * 
	 * @param q The state to transition from
	 * @return The set of states reachable from q using empty string transitions
	 */
	public abstract Set<Integer> eMove(int q);
	
	/**
	 * Creates a transition table from the transition function
	 * 
	 * @return A transition table
	 */
	public HashMap<OrderedPair<Integer, Character>, Set<Integer>> transitionTable() {
		HashMap<OrderedPair<Integer, Character>, Set<Integer>> res = new HashMap<>();
		for (int s: states) {
			for (char c: alphabet) {
				res.put(new OrderedPair<Integer, Character>(s, c), transition(s, c));
			}
		}
		return res;
	}
	
	/**
	 * Returns if the DFAs constructor was used correctly
	 * 
	 * @return If the DFA was constructed properly
	 */
	public boolean ensureCompleteness() {
		return states.contains(startState) &&  finalstates.isSubsetOf(states);
	}
}
