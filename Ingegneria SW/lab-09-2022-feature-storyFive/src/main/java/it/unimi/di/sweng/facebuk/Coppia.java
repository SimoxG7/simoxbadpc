package it.unimi.di.sweng.facebuk;

import java.util.Objects;

class Coppia {

    final String p1;
    final String p2;

    Coppia(String p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coppia)) return false;
        Coppia coppia = (Coppia) o;
        return (p1.equals(coppia.p1) && p2.equals(coppia.p2)) || (p2.equals(coppia.p1) && p1.equals(coppia.p2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}
