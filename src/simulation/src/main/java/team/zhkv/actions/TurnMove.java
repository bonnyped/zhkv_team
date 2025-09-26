package team.zhkv.actions;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class TurnMove extends Turn {
    private final Logger logger = LoggerFactory.getLogger(
            TurnMove.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            List<Map<Location, Entity>> creaturesMaps = gm.getCreaturesMaps();
            for (int i = 0; i < creaturesMaps.size(); ++i) {
                Map<Location, Entity> oldLocations = creaturesMaps.get(i);

                for (var entry : oldLocations.entrySet()) {
                    Creature creature = (Creature) entry.getValue();
                    creaturesMaps.get(i).clear();
                    creaturesMaps.get(i).putAll(creature.makeMove(
                            gm.getWholeMapEntities(), entry.getKey()));
                }

            }
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумениты в
                    класс TurnRender.
                    """);
        }
    }
}
