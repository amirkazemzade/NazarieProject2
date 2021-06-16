package XML;

import Model.Automata;
import Model.State;
import Model.Transition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParser {
    public static void parse(Automata automata, String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(path);

        // Parsing Alphabets Tag
        Node alphabet = document.getElementsByTagName("Alphabets").item(0);
        if (alphabet != null && alphabet.getNodeType() == Node.ELEMENT_NODE) {
            Element alphabetE = (Element) alphabet;
            automata.setNumberOfAlphabets(Integer.parseInt(alphabetE.getAttribute("numberOfAlphabets")));
            NodeList alphabets = alphabetE.getChildNodes();
            for (int i = 0; i < alphabets.getLength(); i++) {
                if (alphabets.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element childE = (Element) alphabets.item(i);
                    automata.addLetterToAlphabet(childE.getAttribute("letter"));
                }
            }
        }

        // Parsing State Tag
        Node state = document.getElementsByTagName("States").item(0);
        if (state != null && state.getNodeType() == Node.ELEMENT_NODE) {
            Element stateE = (Element) state;
            automata.setNumberOfStates(Integer.parseInt(stateE.getAttribute("numberOfStates")));
            NodeList states = stateE.getChildNodes();
            for (int i = 0; i < states.getLength(); i++) {
                if (states.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element childE = (Element) states.item(i);

                    if (childE.getTagName().equals("state")) {
                        // Parsing all states
                        State newState = new State(childE.getAttribute("name"));
                        automata.addState(newState);
                    } else if (childE.getTagName().equals("initialState")) {
                        // Parsing initial state
                        automata.setStartState(childE.getAttribute("name"));
                    } else if (childE.getTagName().equals("FinalStates")) {
                        // Parsing final states
                        automata.setNumberOfFinalStates(Integer.parseInt(childE.getAttribute("numberOfFinalStates")));
                        NodeList finalStates = states.item(i).getChildNodes();
                        for (int j = 0; j < finalStates.getLength(); j++) {
                            if (finalStates.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element finalStatesE = (Element) finalStates.item(j);
                                if (finalStatesE.getTagName().equals("finalState")) {
                                    automata.addFinalState(finalStatesE.getAttribute("name"));
                                }
                            }
                        }
                    }
                }
            }
        }

        // Parsing Transition Tag
        Node transition = document.getElementsByTagName("Transitions").item(0);
        if (transition != null && transition.getNodeType() == Node.ELEMENT_NODE) {
            Element transitionE = (Element) transition;
            automata.setNumberOfTransitions(Integer.parseInt(transitionE.getAttribute("numberOfTrans")));
            NodeList transitions = transitionE.getChildNodes();
            for (int i = 0; i < transitions.getLength(); i++) {
                if (transitions.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element childE = (Element) transitions.item(i);
                    if (childE.getTagName().equals("transition")) {
                        Transition newTransition = new Transition(
                                childE.getAttribute("name"),
                                childE.getAttribute("source"),
                                childE.getAttribute("destination"),
                                childE.getAttribute("input"),
                                childE.getAttribute("pop"),
                                childE.getAttribute("push")
                        );
                        automata.addTransition(newTransition);
                    }
                }
            }
        }
    }
}
