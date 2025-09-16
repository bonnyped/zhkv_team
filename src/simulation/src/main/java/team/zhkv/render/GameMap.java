package team.zhkv.render;

import java.util.Map;
import java.util.TreeSet;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;
import team.zhkv.utils.PropertiesStorage;

public class GameMap {
    private PropertiesStorage storage;
    private Map<Location, Entity> entities;
    private TreeSet<Location> creatures;
    private MapRenderer renderer;

    public Map<Location, Entity> getEntities() {
        return entities;
    }
}
