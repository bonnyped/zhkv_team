package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Herbivore;
import team.zhkv.render.EntitiesStorage;
import team.zhkv.render.Location;

public class InitHerbivores extends Init {
    @Override
    public void action(EntitiesStorage es) {
        Map<Location, Entity> tmp = new HashMap<>();
        for (int i = 0; i < es.differenceEntityCountMinFact(
                Herbivore.class); i++) {
            tmp.put(es.getNewLocation(), new Herbivore());
        }
        es.getObjectsToChange().putAll(tmp);
    }
}
