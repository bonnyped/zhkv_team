package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameManager;
import team.zhkv.core.entities.Entity;
import team.zhkv.actions.move.Coordinate;

public class TurnRespawn extends Turn {
    private static final Logger logger = LoggerFactory.getLogger(
            TurnRespawn.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameManager.class) {
            GameManager gm = (GameManager) obj;
            for (var entry : gm.getRespawnableEntities().entrySet()) {
                gm.getEntities().putAll(respawnEntities(gm, entry));
            }
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумента в
                    класс TurnRespawn.
                    """);
        }
    }

    private Map<Coordinate, Entity> respawnEntities(GameManager gm,
            Map.Entry<Class<? extends Entity>, Integer> entry) {
        Map<Coordinate, Entity> entitiesToRespawn = new HashMap<>();
        Class<? extends Entity> clazz = entry.getKey();
        int diff = gm.differenceBetweenFactAndMinCounts(clazz, entry.getValue());

        for (int i = 0; i < diff; i++) {
            entitiesToRespawn.put(gm.getFreeCoordinate(),
                    gm.getEntityToSpawn(clazz));
        }

        return entitiesToRespawn;
    }
}
