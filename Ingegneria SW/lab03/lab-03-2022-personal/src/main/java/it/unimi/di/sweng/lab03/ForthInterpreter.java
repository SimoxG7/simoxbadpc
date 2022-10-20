package it.unimi.di.sweng.lab03;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ForthInterpreter implements Interpreter {
    private ArrayList<Integer> stack;
    private int index;

    public ForthInterpreter() {
        this.stack = new ArrayList<>();
        this.index = 0;
    }

    @Override
    public void input(String program) {
        reset();

        if (!program.equals("")) {
            String[] data = program.replaceAll("\\s+", " ").split(" "); //removing whitespaces and formatting string

            for (int i = 0; i < data.length; i++) {
                try {
                    stack.add(Integer.parseInt(data[i]));
                    index++;
                } catch (NumberFormatException e) {
                    if (data[i].equals("+")) { //or a binary op
                        stack.set(index - 2, stack.get(index - 2) + stack.get(index - 1));
                        stack.remove(index-1);
                        index--;
                    } else {
                        throw new IllegalArgumentException("Token error '" + data[i] + "'");
                    }
                }
            }
        }
    }

    private void reset() {
        stack = new ArrayList<>();
        index = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            s.append(stack.get(i) + " ");
        }
        s.append("<- Top");
        return s.toString();
    }


}

