package team.zhkv.actions;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;
import team.zhkv.utils.PropertiesStorage;

public class InitAllEntities<T> extends Init<T> {
    @Override
    public void init(T storage) {
        PropertiesStorage ps = (PropertiesStorage) storage;
        HashSet<Location> creatures = new HashSet<>();
        Map<Location, Entity> locations = new HashMap<>();
        Entity[] entities = ps.getAllEntities();
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].getFieldQuantity(); j++) {
                entities[i].setLocation(
                        new Location().getFreeRandomLocation(ps.getFieldSize(),
                                locations));
                locations.put(entities[i].getLocation(), entities[i]);
                if (entities[i].getClass() == Creature.class) {
                    creatures.add(entities[i].getLocation());
                }
            }
        }
        ps.setLocations(locations);
        ps.setCreatures(creatures);
    }
}
