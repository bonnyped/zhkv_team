package team.zhkv.map;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;

public class GameState {
    public static final Coordinate FIELD_SIZE = new Coordinate(20, 50);
    private final Map<Coordinate, Entity> entities = new HashMap<>();
    private final EntityManager em = new EntityManager(entities);
    private final CoordinateManager cm = new CoordinateManager(em.getEntities()
            .keySet());

    private int turnCount;

    public EntityManager getEntityManager() {
        return em;
    }

    public void incrementTurn() {
        turnCount++;
    }

    public Map<Coordinate, Entity> getEntities() {
        return em.getEntities();
    }

    public Map<Coordinate, Entity> getCreaturesMap() {
        Map<Coordinate, Entity> creatures = new HashMap<>();
        for (var entry : em.getEntities().entrySet()) {
            if (Creature.class.isAssignableFrom(entry.getValue().getClass())) {
                creatures.put(entry.getKey(), entry.getValue());
            }
        }
        return creatures;
    }

    public void createAllEntities() {
        for (Entity entity : em.getEntitiesAsList()) {
            Coordinate location = cm.getFreeLocation();
            entities.put(location, entity);
        }
    }

}
