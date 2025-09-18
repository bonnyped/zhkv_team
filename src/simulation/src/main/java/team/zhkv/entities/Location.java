package team.zhkv.entities;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

import team.zhkv.utils.PropertiesStorage;

public class Location {
    private int dx;
    private int dy;

    public Location() {
    }

    public Location(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Location getFreeRandomLocation(PropertiesStorage ps,
            Map<Location, Entity> locations) {
        int sizex = ps.getSizex();
        int sizey = ps.getSizey();

        Random random = new Random(31);
        while (locations.containsKey(this)) {
            dx = random.nextInt(sizex);
            dy = random.nextInt(sizey);
        }
        return this;
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
}
