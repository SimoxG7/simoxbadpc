package it.unimi.di.sweng.katamaestro;

public class GermanPercussionMI implements MusicalInstrument {

    private final GermanPercussion germanPercussion;
    public GermanPercussionMI(GermanPercussion germanPercussion) {
        this.germanPercussion = germanPercussion;
    }

    @Override
    public String play() {
        return germanPercussion.spiel();
    }
}
