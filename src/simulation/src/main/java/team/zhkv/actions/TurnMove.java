package team.zhkv.actions;

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
            Map<Coordinate, Entity> creatures = gm.getCreaturesMap();

            for (var entry : creatures.entrySet()) {
                Creature creature = (Creature) entry.getValue();
                creature.makeMove();
            }
        }
    }
}
