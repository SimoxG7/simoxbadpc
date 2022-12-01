package it.unimi.di.sweng.facebuk;

import java.io.Reader;
import java.util.*;

public class Facebuk {
    private Map<String, List<String>> mapping = new LinkedHashMap<String, List<String>>();
    private FormatStrategy formatStrategy = new DefaultStrategy();

    public Facebuk(Reader input){
        Scanner s = new Scanner(input);
        while(s.hasNextLine()){
            String[] names = s.nextLine().replaceAll("\\p{Punct}"," ").replaceAll("\\s+"," ").split(" ");
            for (int i = 0;i < names.length; i++) {
                String name = names[i];
                char c = name.charAt(0);
                names[i] = name.replaceFirst(String.valueOf(c), String.valueOf(Character.toUpperCase(c)));
            }
            mapping.put(names[0], Arrays.asList(Arrays.copyOfRange(names,1,names.length)));
        }
    }

    public List<String> amiciDi(String nome){
        if(!mapping.containsKey(nome)){
            throw new NoSuchElementException();
        }
        return mapping.get(nome);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String k : mapping.keySet()) {
            sb.append(k + " " + mapping.get(k).toString().replaceAll(",","") + "\n");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public String amiciInComune() {
        StringBuilder sb = new StringBuilder();
        List<Coppia> usati = new ArrayList<>();

        for (String k : mapping.keySet()) {
            for (String nome : mapping.get(k)) {
                Coppia nuova = new Coppia(k, nome);
                if (!contains(usati, nuova)) {
                    usati.add(nuova);
                    sb.append("(" + nuova.p1 + " "  + nuova.p2 + ") ");
                    List<String> incomune = intersection(nuova.p1, nuova.p2);
                    sb.append(incomune.toString().replaceAll(",", ""));
                    sb.append("\n");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private boolean contains(List<Coppia> usati, Coppia nuova) {
        for (Coppia k : usati) {
            if (k.equals(nuova)) return true;
        }
        return false;
    }

    private List<String> intersection(String p1, String p2) {
        List<String> l1 = mapping.get(p1);
        List<String> l2 = mapping.get(p2);
        List<String> incommon = new ArrayList<>();

        for (String n1 : l1) {
            for (String n2 : l2) {
                if (n1.equals(n2) && !incommon.contains(n1)) incommon.add(n1);
            }
        }

        return incommon;
    }

    public void setFormatStrategy(FormatStrategy formatStrategy) {
        this.formatStrategy = formatStrategy;
    }
}
