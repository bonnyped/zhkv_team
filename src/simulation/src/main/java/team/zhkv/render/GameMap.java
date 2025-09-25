package team.zhkv.render;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import team.zhkv.entities.Eatable;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.utils.PropertiesStorage;

public class GameMap {
    private PropertiesStorage storage = new PropertiesStorage();

    private Map<Location, Entity> locations = new HashMap<>();

    private Set<Location> creatures = new HashSet<>();

    private MapRenderer renderer = new MapRenderer();

    private int grassCount = storage.getMinGrassQuantity();

    private int herbivoresCount = storage.getMinHerbivorePopulation();

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

    public void incrementHerbivoreCount() {
        ++herbivoresCount;
    }

    public int differenceHerbivoreCountAndMin() {
        return storage.getMinHerbivorePopulation() - herbivoresCount;
    }

    public void incrementGrassCount() {
        ++grassCount;
    }

    public int differenceGrassCountAndMin() {
        return storage.getMinGrassQuantity() - grassCount;
    }

    public void decrementEatable(Location isEatable) {
        if (locations.get(isEatable) instanceof Grass) {
            --grassCount;
        } else if (locations.get(isEatable) instanceof Herbivore) {
            --herbivoresCount;
        }
    }

}
