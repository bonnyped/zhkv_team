package team.zhkv.actions;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class InitAllEntities extends Init {
    private boolean isInited;

    @Override
    public void init(GameMap gm) {
        if (!isInited) {
            HashSet<Location> creatures = new HashSet<>();
            Map<Location, Entity> locations = new HashMap<>();

            Entity[] entities = gm.getAllEntities();
            for (int i = 0; i < entities.length; i++) {
                for (int j = 0; j < entities[i].getQuantity(); j++) {
                    createIntities(gm, entities[i]);
                }
            }
            gm.setLocations(locations);
            gm.setCreatures(creatures);
            isInited = true;
        }
    }
}
