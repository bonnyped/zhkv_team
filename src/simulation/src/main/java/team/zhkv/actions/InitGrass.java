package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.render.EntitiesStorage;
import team.zhkv.render.Location;

public class InitGrass extends Init {
    @Override
    public void action(EntitiesStorage es) {
        Map<Location, Entity> tmp = new HashMap<>();
        for (int i = 0; i < es.differenceEntityCountMinFact(
                Grass.class); i++) {
            tmp.put(es.getNewLocation(), new Grass());
        }
        es.getObjectsToChange().putAll(tmp);
    }
}
