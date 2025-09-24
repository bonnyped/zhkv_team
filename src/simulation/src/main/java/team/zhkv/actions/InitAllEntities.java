package team.zhkv.actions;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import team.zhkv.App;
import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class InitAllEntities extends Init {
    @Override
    public void init(GameMap gm) {
        HashSet<Location> creatures = new HashSet<>();
        Map<Location, Entity> locations = new HashMap<>();

        Entity[] entities = gm.getAllEntities();
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].getEntitiesQuantityOnMap(); j++) {
                Location randomUniqueLocation = new Location()
                        .getFreeRandomLocation(App.FIELD_SIZE,
                                locations);
                locations.put(randomUniqueLocation, entities[i]);
                if (entities[i] instanceof Creature) {
                    creatures.add(randomUniqueLocation);
                }
            }
        }
        gm.setLocations(locations);
        gm.setCreatures(creatures);
    }
}
