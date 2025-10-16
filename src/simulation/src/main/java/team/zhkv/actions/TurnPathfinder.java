package team.zhkv.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameMap;
import team.zhkv.core.entities.Creature;
import team.zhkv.actions.move.Coordinate;

public class TurnPathfinder extends Turn {
    private static final Logger logger = LoggerFactory.getLogger(
            TurnPathfinder.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Creature> creaturesMap = gm.getCreaturesMap();

            for (var entry : creaturesMap.entrySet()) {
                entry.getValue().findPath(gm, entry.getKey());
            }
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумента в
                    класс TurnRender.
                    """);
        }
    }
}
