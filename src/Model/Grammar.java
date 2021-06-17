package Model;

import java.util.ArrayList;

public class Grammar {
    private final String start;
    private final ArrayList<String> end;

    public Grammar(String start) {
        this.start = start;
        this.end = new ArrayList<>();
    }

    public void addEnd(String newEnd){
        end.add(newEnd);
    }

    public String getStart() {
        return start;
    }

    public ArrayList<String> getEnd() {
        return end;
    }
}
