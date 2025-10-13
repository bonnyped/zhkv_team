package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameMap;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.Herbivore;
import team.zhkv.actions.move.Coordinate;

public class TurnHerbivores extends Turn {
    private static final Logger logger = LoggerFactory.getLogger(TurnHerbivores.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Entity> tmp = new HashMap<>();
            for (int i = 0; i < gm.differenceEntityCountMinFact(
                    Herbivore.class); i++) {
                Coordinate location = gm.getNewLocation();
                tmp.put(location, new Herbivore());
            }
            gm.getMapByEntity(Herbivore.class).putAll(tmp);
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумениты в
                    класс TurnHerbivores.
                    """);
        }
    }
}
