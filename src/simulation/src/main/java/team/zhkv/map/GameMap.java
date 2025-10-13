package team.zhkv.map;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;

public class GameMap {
    public static final int DX = 20;
    public static final int DY = 50;
    private final Map<Coordinate, Entity> entities = new HashMap<>();
    private final EntityManager em = new EntityManager();
    private final CoordinateManager cm = new CoordinateManager(em.getEntities()
            .keySet());

    private int turnCount;

    public int getTurnCount() {
        return turnCount;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void incrementTurn() {
        ++turnCount;
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public Map<Coordinate, Entity> getEntities() {
        return em.getEntities();
    }

    public void spawnAllEntities() {
        for (Entity entity : em.getEntitiesAsList()) {
            Coordinate location = cm.getFreeLocation();

            entities.put(location, entity);
        }
    }

    public Map<Coordinate, Entity> getCreaturesMap() {
        return em.getCreaturesMap();
    }

}
