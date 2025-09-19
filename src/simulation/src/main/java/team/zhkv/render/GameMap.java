package team.zhkv.render;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;

public class GameMap {
    private Map<Location, Entity> locations;

    private HashSet<Location> creatures;

    private MapRenderer renderer;

    public GameMap(Map<Location, Entity> locations,
            Set<Location> creatures, Location fieldSize) {
        this.locations = locations;
        this.creatures = (HashSet<Location>) creatures;
        renderer = new MapRenderer(fieldSize);
    }

    public void render() {
        renderer.render(locations);
    }

    public Set<Location> getCreatures() {
        return creatures;
    }

    public MapRenderer getRenderer() {
        return renderer;
    }

    public Map<Location, Entity> getLocations() {
        return locations;
    }

    public void setCreatures(Set<Location> creatures) {
        if (creatures.getClass() == HashSet.class) {
            creatures.clear();
            this.creatures = (HashSet<Location>) creatures;
        }
    }
}
