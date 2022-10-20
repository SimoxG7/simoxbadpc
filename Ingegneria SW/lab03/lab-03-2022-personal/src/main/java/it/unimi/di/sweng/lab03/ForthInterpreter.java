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
                    switch (datum) {
                        case "+":
                            binaryOp("+");
                            break;
                        case "-":
                            binaryOp("-");
                            break:
                        case "*":
                            binaryOp("*");
                            break;
                        case "/":
                            binaryOp("/");
                            break;
                        case "swap":
                            binaryOp("swap");
                            break;
                        case "dup":
                            unaryOp("dup");
                            break;
                        default:
                            throw new IllegalArgumentException("Token error '" + datum + "'");
                    }
                }
            }
        }
    }

    private boolean underflow() {
        return stack.size() <= 1;
    }

    private void binaryOp(String op) {
        
    }

    private void unaryOp(String op) {
        //TODO
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

