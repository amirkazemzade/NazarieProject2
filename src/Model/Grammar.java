package Model;

import java.util.ArrayList;

public class Grammar {
    private String start;
    private ArrayList<String> end;

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
