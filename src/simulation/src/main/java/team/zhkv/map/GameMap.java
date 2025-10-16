package team.zhkv.map;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;
import team.zhkv.core.interfaces.IEdible;

public class GameMap {
    public static final int DX = 50;
    public static final int DY = 20;

    private final Map<Coordinate, Entity> entities;
    private final EntityManager em;
    private final CoordinateManager cm;

    private int turnCount;

    public GameMap() {
        entities = new HashMap<>();
        em = new EntityManager(entities);
        cm = new CoordinateManager(em.getEntities()
                .keySet());
        turnCount = 0;
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

    public Map<Coordinate, Creature> getCreaturesMap() {
        return em.getCreaturesMap();
    }

    public Map<Coordinate, IEdible> getEdiblesMap() {
        return em.getEdiblesMap();
    }

    public int differenceBetweenFactAndMinCounts(Class<? extends Entity> clazz,
            int requered) {
        int fact = getEntityFactCount(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    public Map<Class<? extends Entity>, Integer> getSpawnedEntities() {
        return em.getSpawnedEntities();
    }

    public Entity getEntityToSpawn(Class<? extends Entity> clazz) {
        return em.getNewEntity(clazz);
    }

    public Coordinate getBounds() {
        return new Coordinate(DX, DY);
    }

    public void updateCreatureCoordinate(Coordinate src, Coordinate target) {
        entities.put(target, entities.remove(src));
    }

    public void removeEatedEntity(Coordinate toRemove) {
        em.removeEntity(toRemove);
    }

    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact < requered / 2;
    }

    private int getEntityFactCount(Class<? extends Entity> clazz) {
        return Math.toIntExact(entities.values()
                .stream()
                .filter(clazz::isInstance)
                .count());
    }

}
