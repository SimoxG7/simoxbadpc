package it.unimi.di.sweng.katamaestro;

public class WaterGlassMI extends WaterGlass implements MusicalInstrument  {

    @Override
    public String play() {
        return tap();
    }
}
