package team.zhkv.actions;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;
import team.zhkv.utils.PropertiesStorage;

public class InitAllEntities extends Init {
    @Override
    public void init(Object storage) {
        HashSet<Location> creatures = new HashSet<>();
        Map<Location, Entity> locations = new HashMap<>();
        PropertiesStorage ps = null;

        if (storage.getClass() == PropertiesStorage.class) {
            ps = (PropertiesStorage) storage;
            Entity[] entities = ps.getAllEntities();
            for (int i = 0; i < entities.length; i++) {
                for (int j = 0; j < entities[i].getFieldQuantity(); j++) {
                    entities[i].setLocation(
                            new Location()
                                    .getFreeRandomLocation(ps.getFieldSize(),
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
}
