package de.dknuth.adventofcode23.day03;

public class Coordinate {

    final int i;
    final int j;

    private Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static Coordinate of(int i, int j) {
        return new Coordinate(i, j);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        result = prime * result + j;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (i != other.i)
            return false;
        if (j != other.j)
            return false;
        return true;
    }
}
