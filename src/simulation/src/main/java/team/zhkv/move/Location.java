package team.zhkv.move;

import java.util.Objects;

public class Location {
    private int dx;
    private int dy;

    public Location() {
    }

    public Location(Location other) {
        dx = other.dx;
        dy = other.dy;
    }

    public Location(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    private Location setLocation(Location other) {
        this.dx += other.dx;
        this.dy += other.dy;
        return this;
    }

    public boolean isInBounds(Location fieldSize) {
        return dx >= 0
                && dx < fieldSize.dx
                && dy >= 0
                && dy < fieldSize.dy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != Location.class) {
            return false;
        }
        Location other = (Location) obj;

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

    public Location setLocation(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    public Location getNeighbor(Location other) {
        return new Location(this).setLocation(other);
    }

    @Override
    public String toString() {
        return "x = " + dx + "y = " + dy;
    }

}
