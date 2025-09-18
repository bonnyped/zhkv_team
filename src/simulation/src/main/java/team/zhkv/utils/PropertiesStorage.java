package team.zhkv.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Tree;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Location;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;

public class PropertiesStorage {
    private Location fieldSize = new Location(10, 10);

    private int maxEntitiesCount = fieldSize.getDx() * fieldSize.getDy() / 2;
    private int minHerbivorePopulation = maxEntitiesCount / 7;
    private int minGrassQuantity = minHerbivorePopulation;

    private Map<Location, Entity> locations;
    private HashSet<Location> creatures;

    private Entity[] allEntities = { new Tree(maxEntitiesCount),
            new Rock(maxEntitiesCount),
            new Grass(maxEntitiesCount),
            new Herbivore(maxEntitiesCount),
            new Predator(maxEntitiesCount) };

    public Location getFieldSize() {
        return fieldSize;
    }

    public Entity[] getAllEntities() {
        return allEntities;
    }

    public int getMinGrassQuantity() {
        return minGrassQuantity;
    }

    public Map<Location, Entity> getLocations() {
        return locations;
    }

    public void setLocations(Map<Location, Entity> locations) {
        this.locations = locations;
    }

    public Set<Location> getCreatures() {
        return creatures;
    }

    public void setCreatures(Set<Location> creatures) {
        this.creatures = (HashSet<Location>) creatures;
    }
}
