package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CFG {

    public static ArrayList<Grammar> convertToCFG(Automata automata) {
        ArrayList<Grammar> grammars = new ArrayList<>();
        configureTransitions(automata);

        for (Transition transition : automata.getTransitions()) {

            if (transition.getPush().equals("0")) {
                Grammar currentGrammar = getGrammar(
                        transition.getSource() + transition.getPop() + transition.getDestination(),
                        grammars
                );
                currentGrammar.addEnd(transition.getInput());
            } else if (transition.getPop().equals("z")) {
                for (State outerState : automata.getStates()) {
                    for (State innerState : automata.getStates()) {

                        Grammar currentGrammar = getGrammar(
                                transition.getSource() + transition.getPop() + outerState.getName(),
                                grammars
                        );

                        if (transition.getInput().equals("0")) {
                            currentGrammar.addEnd(
                                    "(" + transition.getDestination() + transition.getPush().charAt(0) + innerState.getName() + ")" +
                                            "(" + innerState.getName() + transition.getPush().charAt(1) + outerState.name + ")"
                            );
                        } else {
                            currentGrammar.addEnd(
                                    transition.getInput() +
                                            "(" + transition.getDestination() + transition.getPush().charAt(0) + innerState.getName() + ")" +
                                            "(" + innerState.getName() + transition.getPush().charAt(1) + outerState.name + ")"
                            );
                        }
                    }
                }
            }
        }
        return grammars;
    }

    private static void configureTransitions(Automata automata) {
        int qIndex = automata.getNumberOfAlphabets() + 1;
        Queue<Transition> replaceList = new LinkedList<>();
        for (Transition transition : automata.getTransitions()) {
            if (!transition.getPop().equals("z") && !transition.getPush().equals("0")) {
                replaceList.add(transition);
            }
        }
        while (!replaceList.isEmpty()) {
            Transition transition = replaceList.poll();
            State newState = new State("q" + qIndex++);

            Transition replaceTo1 = new Transition(
                    transition.getName() + "0",
                    transition.getSource(),
                    newState.name,
                    transition.getInput(),
                    transition.getPop(),
                    "0"
            );

            Transition replaceTo2 = new Transition(
                    transition.getName() + "1",
                    newState.name,
                    transition.getDestination(),
                    "0",
                    "z",
                    transition.getPush() + "z"
            );

            automata.replaceTransition(transition, replaceTo1, replaceTo2, newState);
        }
    }

    private static Grammar getGrammar(String start, ArrayList<Grammar> grammars) {
        for (Grammar grammar : grammars) {
            if (grammar.getStart().equals(start)) {
                return grammar;
            }
        }
        Grammar newGrammar = new Grammar(start);
        grammars.add(newGrammar);
        return newGrammar;
    }

    public static void PrintGrammar(ArrayList<Grammar> grammars, String path) throws IOException {
        File output = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        for (Grammar gram : grammars) {
            writer.write("(" + gram.getStart() + ") -> ");
            boolean isFirstRound = true;
            for (String end : gram.getEnd()) {
                if (!isFirstRound) {
                    writer.write("| ");
                }
                isFirstRound = false;
                if (end.equals("0")) {
                    writer.write("lambda ");
                } else {
                    writer.write(end + " ");
                }
            }
            writer.newLine();
        }
        writer.close();
    }
}
