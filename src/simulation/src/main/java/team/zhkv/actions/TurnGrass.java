package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.GameMap;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.move.Location;

public class TurnGrass extends Turn {
    private static final Logger logger = LoggerFactory.getLogger(TurnGrass.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Location, Entity> tmp = new HashMap<>();
            for (int i = 0; i < gm.differenceEntityCountMinFact(
                    Grass.class); i++) {
                Location location = gm.getNewLocation();
                tmp.put(location, new Grass());
            }
            gm.getMapByEntity(Grass.class).putAll(tmp);
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумента в
                    класс TurnGrass.
                    """);
        }
    }
}
