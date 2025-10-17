package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Herbivore;
import team.zhkv.map.GameMap;

public class TurnMove extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Creature> creatures = gm.getCreaturesMap();

            for (var creature : creatures.entrySet()) {
                Coordinate coordinate = creature.getKey();
                // coordinate == new Coordinate()
                creature.getValue().makeMove();
            }
        }
    }
}
