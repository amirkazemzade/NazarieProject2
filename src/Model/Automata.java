package Model;

import java.util.ArrayList;

public class Automata {

    private int numberOfAlphabets;
    private final ArrayList<String> alphabet;

    private int numberOfStates;
    private final ArrayList<State> states;

    private String startState;

    private int numberOfFinalStates;
    private final ArrayList<String> finalStates;

    private int numberOfTransitions;
    private final ArrayList<Transition> transitions;

    public Automata() {
        alphabet = new ArrayList<>();
        states = new ArrayList<>();
        finalStates = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public int getNumberOfAlphabets() {
        return numberOfAlphabets;
    }

    public void setNumberOfAlphabets(int numberOfAlphabets) {
        this.numberOfAlphabets = numberOfAlphabets;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void addLetterToAlphabet(String letter) {
        this.alphabet.add(letter);
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }

    public void addFinalState(String finalState) {
        this.finalStates.add(finalState);
    }

    public int getNumberOfTransitions() {
        return numberOfTransitions;
    }

    public void setNumberOfTransitions(int numberOfTransitions) {
        this.numberOfTransitions = numberOfTransitions;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public void replaceTransition(Transition replaceFrom, Transition replaceTo1, Transition replaceTo2, State newState) {
        transitions.remove(replaceFrom);
        transitions.add(replaceTo1);
        transitions.add(replaceTo2);
        numberOfTransitions++;

        states.add(newState);
        numberOfStates++;
    }

    public int getNumberOfFinalStates() {
        return numberOfFinalStates;
    }

    public void setNumberOfFinalStates(int numberOfFinalStates) {
        this.numberOfFinalStates = numberOfFinalStates;
    }
}
