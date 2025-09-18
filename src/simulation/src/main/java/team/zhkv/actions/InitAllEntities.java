package team.zhkv.actions;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;
import team.zhkv.utils.PropertiesStorage;

public class InitAllEntities<T> extends Init<T> {
    private Map<Location, Entity> locations = new HashMap<>();

    private HashSet<Location> creatures = new HashSet<>();

    @Override
    public void init(T storage) {
        PropertiesStorage ps = (PropertiesStorage) storage;
        Entity[] entities = ps.getEntities();
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].getFieldQuantity(); j++) {
                entities[i].setLocation(
                        new Location().getFreeRandomLocation(ps,
                                locations));
                locations.put(entities[i].getLocation(), entities[i]);
                if (entities[i].getClass() == Creature.class) {
                    creatures.add(entities[i].getLocation());
                }
            }
        }
    }

    public Map<Location, Entity> getLocations() {
        return locations;
    }

    public void setLocations(Map<Location, Entity> locations) {
        this.locations = locations;
    }

    public HashSet<Location> getCreatures() {
        return creatures;
    }

    public void setCreatures(HashSet<Location> creatures) {
        this.creatures = creatures;
    }
}
