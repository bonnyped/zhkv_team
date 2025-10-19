package team.zhkv.actions;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.map.GameManager;
import team.zhkv.core.entities.Entity;
import team.zhkv.actions.move.Coordinate;

public class TurnRespawn extends Turn {
    @Override
    public void action(GameManager gm) {
        for (var entry : gm.getRespawnableEntities().entrySet()) {
            gm.getEntities().putAll(respawnEntities(gm, entry));
        }
    }

    private Map<Coordinate, Entity> respawnEntities(GameManager gm,
            Map.Entry<Class<? extends Entity>, Integer> entry) {
        Map<Coordinate, Entity> entitiesToRespawn = new HashMap<>();
        Class<? extends Entity> clazz = entry.getKey();
        int diff = gm.getDiffCountsMinFact(clazz, entry.getValue());

        for (int i = 0; i < diff; i++) {
            entitiesToRespawn.put(gm.getFreeCoordinate(),
                    gm.getEntityToSpawn(clazz));
        }

        return entitiesToRespawn;
    }
}
