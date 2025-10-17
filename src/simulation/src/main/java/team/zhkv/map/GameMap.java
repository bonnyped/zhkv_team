package team.zhkv.map;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;

public class GameMap {
    public static final int DX = 50;
    public static final int DY = 20;

    private final EntityManager em;
    private final CoordinateManager cm;

    private int turnCount;

    public GameMap() {
        em = new EntityManager(new HashMap<>());
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
        return em.getEntity(coordinate);
    }

    public Map<Coordinate, Entity> getEntities() {
        return em.getEntities();
    }

    public void spawnAllEntities() {
        for (Entity entity : em.getEntitiesAsList()) {
            em.putEntity(getFreeCoordinate(), entity);
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

    public Map<Coordinate, IEater> getEatersMap() {
        return em.getEatersMap();
    }

    public Map<Coordinate, IDamager> getDamagersMap() {
        return em.getDamagersMap();
    }

    public int differenceBetweenFactAndMinCounts(Class<? extends Entity> clazz,
            int requered) {
        int fact = getEntityFactCount(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    public Map<Class<? extends Entity>, Integer> getRespawnableEntities() {
        return em.getRespawnableEntities();
    }

    public Entity getEntityToSpawn(Class<? extends Entity> clazz) {
        return em.getNewEntity(clazz);
    }

    @SuppressWarnings("java:S3824")
    public void updateCreatureCoordinate(Coordinate src, Coordinate target) {
        if (!em.isOccupiedCoordinate(target)) {
            em.putEntity(target, em.removeEntity(src));
        }
    }

    public void removeEatedEntity(Coordinate toRemove) {
        em.removeEntity(toRemove);
    }

    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact <= requered / 2;
    }

    private int getEntityFactCount(Class<? extends Entity> clazz) {
        return Math.toIntExact(em.getEdiblesMap().values()
                .stream()
                .filter(clazz::isInstance)
                .count());
    }

}
