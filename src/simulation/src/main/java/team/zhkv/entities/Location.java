package team.zhkv.entities;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

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

    public Location getFreeRandomLocation(Location fieldSize,
            Map<Location, Entity> locations) {
        Random random = new Random(31);
        while (locations.containsKey(this)) {
            dx = random.nextInt(fieldSize.dx);
            dy = random.nextInt(fieldSize.dy);
        }
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

}
