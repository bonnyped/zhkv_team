package team.zhkv.actions.move;

import java.util.Objects;

import team.zhkv.map.GameManager;

public class Coordinate {
    private int dx;
    private int dy;

    public Coordinate() {
    }

    public Coordinate(Coordinate other) {
        dx = other.dx;
        dy = other.dy;
    }

    public Coordinate(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    private Coordinate setCoordinate(Coordinate other) {
        this.dx += other.dx;
        this.dy += other.dy;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != Coordinate.class) {
            return false;
        }
        Coordinate other = (Coordinate) obj;

        return dx == other.dx && dy == other.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Coordinate setCoordinate(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    public Coordinate getNeighbor(Coordinate other) {
        return new Coordinate(this).setCoordinate(other);
    }

    @Override
    public String toString() {
        return "x = " + dx + " y = " + dy;
    }

}
