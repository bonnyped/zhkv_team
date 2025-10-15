package team.zhkv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;

public class GameMap {
    public static final int DX = 20;
    public static final int DY = 50;

    private final Map<Coordinate, Entity> entities;
    private final EntityManager em;
    private final CoordinateManager cm;
    private final Map<Coordinate, List<Coordinate>> entitiesToMove;

    private int turnCount;

    public GameMap() {
        entities = new HashMap<>();
        em = new EntityManager();
        cm = new CoordinateManager(em.getEntities()
                .keySet());
        entitiesToMove = new HashMap<>();
        turnCount = 0;
    }

    public Map<Coordinate, List<Coordinate>> getEntitiesToMove() {
        return entitiesToMove;
    }

    public int getTurnCount() {
        return turnCount;
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
            entities.put(getFreeCoordinate(), entity);
        }
    }

    public Coordinate getFreeCoordinate() {
        return cm.getFreeCoordinate();
    }

    public Map<Coordinate, Entity> getCreaturesMap() {
        return em.getCreaturesMap();
    }

    public int differenceBetweenFactAndMinCounts(Class<? extends Entity> clazz, int requered) {
        int fact = getEntityFactCount(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    public Map<Class<? extends Entity>, Integer> getSpawnedEntities() {
        return em.getSpawnedEntities();
    }

    public Entity getEntityToSpawn(Class<? extends Entity> clazz) {
        return em.getNewEntity(clazz);
    }

    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact < requered / 2;
    }

    public Coordinate getBounds() {
        return new Coordinate(DX, DY);
    }

    private int getEntityFactCount(Class<? extends Entity> clazz) {
        return Math.toIntExact(entities.values()
                .stream()
                .filter(clazz::isInstance)
                .count());
    }

}
