package it.unimi.di.sweng.lab03;

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

            for (String datum : data) {
                try {
                    stack.add(Integer.parseInt(datum));
                    index++;
                } catch (NumberFormatException e) {
                    if (datum.equals("+")) { //or a binary op
                        if (underflow()) throw new IllegalArgumentException("Stack Underflow");
                        stack.set(index - 2, stack.get(index - 2) + stack.get(index - 1));
                        stack.remove(index - 1);
                        index--;
                    } else {
                        throw new IllegalArgumentException("Token error '" + datum + "'");
                    }
                }
            }
        }
    }

    private boolean underflow() {
        return stack.size() <= 1;
    }

    private void reset() {
        stack = new ArrayList<>();
        index = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer i : stack) {
            s.append(i).append(" ");
        }
        s.append("<- Top");
        return s.toString();
    }


}

