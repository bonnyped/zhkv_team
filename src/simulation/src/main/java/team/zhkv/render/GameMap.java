package team.zhkv.render;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.utils.PropertiesStorage;

public class GameMap {
    private PropertiesStorage storage = new PropertiesStorage();

    private Map<Location, Entity> locations;

    private Set<Location> creatures;

    private MapRenderer renderer = new MapRenderer();

    public void render() {
        renderer.render(locations);
    }

    public Set<Location> getCreatures() {
        return creatures;
    }

    public MapRenderer getRenderer() {
        return renderer;
    }

    public void setLocations(Map<Location, Entity> locations) {
        this.locations = locations;
    }

    public Map<Location, Entity> getLocations() {
        return locations;
    }

    public void setCreatures(Set<Location> creatures) {
        if (creatures != null && creatures.getClass() == HashSet.class) {
            this.creatures = creatures;
        }
    }

    public Entity[] getAllEntities() {
        return storage.getAllEntities();
    }
}
