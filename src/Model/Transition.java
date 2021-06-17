package Model;

public class Transition {
    private final String name;
    private final String source;
    private final String destination;
    private final String input;
    private final String pop;
    private final String push;


    public Transition(String name, String source, String destination, String input, String pop, String push) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.input = input;
        this.pop = pop;
        this.push = push;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getInput() {
        return input;
    }

    public String getPop() {
        return pop;
    }

    public String getPush() {
        return push;
    }
}
