package it.unimi.di.sweng.lab03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ForthInterpreter implements Interpreter {
    private ArrayList<Integer> stack;
    private int index;
    private HashMap<String, String[]> dict;

    public ForthInterpreter() {
        this.stack = new ArrayList<>();
        this.index = 0;
        this.dict = new HashMap<>();
    }

    @Override
    public void input(String program) {
        reset();

        if (!program.equals("")) {
            String[] data = program.replaceAll("\\s+", " ").split(" "); //removing whitespaces and formatting string

            interpret(data);
        }
    }

    private void interpret(String[] data) {
        int dataindex = 0;
        while (dataindex < data.length){
            String datum = data[dataindex];
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
                    case "drop":
                        unaryOp("drop");
                        break;
                    case ":":
                        dataindex++;
                        dataindex = wordDefinition(data, dataindex);
                        break;
                    default:
                        if (dict.containsKey(datum)) {
                            subLevelOperation(datum);
                        } else {
                            throw new IllegalArgumentException("Token error '" + datum + "'");
                        }
                }
            }
            dataindex++;
        }
    }

    private void subLevelOperation(String datum) {
        String[] operation = dict.get(datum);
        //interpret(operation);
        for (String step : operation) {
            try {
                stack.add(Integer.parseInt(step));
                index++;
            } catch (NumberFormatException e) {
                switch (step) {
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
                    case "drop":
                        unaryOp("drop");
                        break;
                    default:
                        if (dict.containsKey(step)) {
                            subLevelOperation(step);
                        } else {
                            throw new IllegalArgumentException("Token error '" + step + "'");
                        }
                }
            }
        }
    }

    private int wordDefinition(String[] data, int dataindex) {
        String key = data[dataindex++];
        StringBuilder command = new StringBuilder();
        while (!data[dataindex].equals(";")) {
            command.append(data[dataindex++]).append(" ");
        }
        dict.put(key, command.toString().split(" "));
        return dataindex;
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

    private void unaryOp(String op) {
        underflow(1);
        if (op.equals("dup")) {
            stack.add(stack.get(index++ - 1));
        } else if (op.equals("drop")) {
            stack.remove(index-1);
            index--;
        } else {
            throw new IllegalArgumentException("Invalid operand");
        }
    }

    private void reset() {
        this.stack = new ArrayList<>();
        this.index = 0;
        this.dict = new HashMap<>();
    }


    private void underflow(int arity) {
        if (stack.size() < arity) {
            throw new IllegalArgumentException("Stack Underflow");
        }
    }

    private void binaryOp(String op) {
        underflow(2);
        switch (op) {
            case "+":
                stack.set(index - 2, stack.get(index - 2) + stack.get(index - 1));
                index--;
                stack.remove(index);
                break;
            case "-":
                stack.set(index - 2, stack.get(index - 2) - stack.get(index - 1));
                index--;
                stack.remove(index);
                break;
            case "*":
                stack.set(index - 2, stack.get(index - 2) * stack.get(index - 1));
                index--;
                stack.remove(index);
                break;
            case "/":
                stack.set(index - 2, stack.get(index - 2) / stack.get(index - 1));
                index--;
                stack.remove(index);
                break;
            case "swap":
                int temp = stack.get(index - 2);
                stack.set(index - 2, stack.get(index - 1));
                stack.set(index - 1, temp);
                break;
            default:
                throw new IllegalArgumentException("Invalid operand.");
        }
    }
}

