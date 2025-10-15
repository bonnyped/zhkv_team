package team.zhkv.actions;

import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.map.GameMap;

public class TurnMove extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Entity> entities = gm.getEntities();
            Map<Coordinate, List<Coordinate>> paths = gm.getEntitiesToMove();

            for (var entry : paths.entrySet()) {
                Creature creature = (Creature) entities.get(entry.getKey());
                creature.makeMove(gm, entry);
            }

            /*
             * if (gm.getEntity(target) == null) {
             * Entity entity = gm.getEntity(start);
             * var map = gm.getMapByEntity(entity.getClass());
             * map.remove(start);
             * map.put(target, entity);
             * }
             */
        }
    }
}
