package team.zhkv.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public class TurnMove extends Turn {
    private final Logger logger = LoggerFactory.getLogger(
            TurnMove.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            List<Map<Location, Entity>> creaturesMaps = gm.getCreaturesMaps();
            for (int i = 0; i < creaturesMaps.size(); ++i) {
                Iterator<Entry<Location, Entity>> it = creaturesMaps.get(i)
                        .entrySet()
                        .iterator();
                while (it.hasNext()) {
                    var entry = it.next();
                    Creature creature = (Creature) entry.getValue();
                    creature.makeMove(gm, entry.getKey());
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
