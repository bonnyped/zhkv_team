package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Herbivore;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class InitHerbivores extends Init {
    private final Logger logger = LoggerFactory.getLogger(InitHerbivores.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Location, Entity> tmp = new HashMap<>();
            for (int i = 0; i < gm.differenceEntityCountMinFact(
                    Herbivore.class); i++) {
                tmp.put(gm.getNewLocation(), new Herbivore());
            }
            gm.getObjectsToChange().putAll(tmp);
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумениты в
                    класс InitHerbivores.
                    """);
        }
    }
}
