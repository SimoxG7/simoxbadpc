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
                            break;
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
        switch (op) {
            case "+":
                stack.set(index-2, stack.get(index-2) + stack.get(index-1));
                index--;
                stack.remove(index);
                break;
            case "-":
                stack.set(index-2, stack.get(index-2) - stack.get(index-1));
                index--;
                stack.remove(index);
                break;
            case "*":
                stack.set(index-2, stack.get(index-2) * stack.get(index-1));
                index--;
                stack.remove(index);
                break;
            case "/":
                stack.set(index-2, stack.get(index-2) / stack.get(index-1));
                index--;
                stack.remove(index);
                break;
            case "swap":
                int temp = stack.get(index-2);
                stack.set(index-2, stack.get(index-1));
                stack.set(index-1, temp);
                break;
            default:
                throw new IllegalArgumentException("Invalid operand.");
    }

    private void unaryOp(String op) {
        if (op.equals("dup")) {
            stack.set(index++, stack.get(index-1));
        } else {
            throw new IllegalArgumentException("Invalid operand");
        }
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

