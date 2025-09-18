package team.zhkv.render;

import java.util.TreeSet;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;
import team.zhkv.utils.PropertiesStorage;

public class GameMap {
    private PropertiesStorage storage = new PropertiesStorage();

    private Map<Location, Entity> entities;

    private TreeSet<Location> creatures;

    private MapRenderer renderer;

    public Map<Location, Entity> getEntities() {
        return entities;
    }

    public PropertiesStorage getStorage() {
        return storage;
    }

    public TreeSet<Location> getCreatures() {
        return creatures;
    }

    public void setEntities(Map<Location, Entity> entities) {
        this.entities = entities;
    }

    public void setCreatures(TreeSet<Location> creatures) {
        this.creatures = creatures;
    }
}
