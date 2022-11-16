package it.unimi.di.sweng.katamaestro;

import java.util.ArrayList;
import java.util.List;

public class Orchestra implements MusicalInstrument {

    private final List<MusicalInstrument> elements = new ArrayList<>();
    @Override
    public String play() {
        StringBuilder s = new StringBuilder();

        for (MusicalInstrument element : elements) {
            s.append(element.play()).append("\n");
        }
        if (!s.isEmpty()) s.deleteCharAt(s.length()-1);
        return s.toString();
    }

    public void add(MusicalInstrument musicalInstrument) {
        elements.add(musicalInstrument);
    }
}
