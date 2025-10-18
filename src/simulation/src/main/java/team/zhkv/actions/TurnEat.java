package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameMap;

public class TurnEat extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, IEater> eaters = gm.getEatersMap();

            for (var eater : eaters.entrySet()) {
                Entity entity = gm.getEntity(eater.getValue().getGoalCoordinate());
                if (entity != null
                        && eater.getValue().getFood() == entity.getClass()) {
                    eater.getValue().eat((IEdible) entity);
                }
            }
        }
    }
}
