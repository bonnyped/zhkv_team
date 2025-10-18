package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Creature;
import team.zhkv.map.GameMap;

public class TurnMove extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Creature> creatures = gm.getCreaturesMap();

            for (var creature : creatures.entrySet()) {
                creature.getValue().makeMove();
            }
        }
    }
}
