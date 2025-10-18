package team.zhkv.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;

public class GameManager {
    public static final int DX = 50;
    public static final int DY = 20;

    private final EntityManager em;
    private final CoordinateManager cm;

    private int turnCount;

    public GameManager() {
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

    public void spawnAllEntities() {
        for (Entity entity : em.getEntitiesAsList()) {
            em.putEntity(getFreeCoordinate(), entity);
        }
    }

    public Coordinate getFreeCoordinate() {
        return cm.getFreeCoordinate();
    }

    public <T> List<T> getSpecificEntitiesByClass(Class<T> clazz) {
        return em.getSpecificEntitiesByClass(clazz);
    }

    public int differenceBetweenFactAndMinCounts(Class<? extends Entity> clazz,
            int requered) {
        int fact = getFactNumberOfSpecificEntity(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    public Entity getEntityToSpawn(Class<? extends Entity> clazz) {
        return em.getNewEntity(clazz);
    }

    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return em.isOccupiedCoordinate(coordinate);
    }

    @SuppressWarnings("java:S3824")
    public void updateCreatureCoordinate(Coordinate src, Coordinate target) {
        if (!isOccupiedCoordinate(target)) {
            em.putEntity(target, em.removeEntity(src));
        }
    }

    public void removeEatedEntity(Coordinate toRemove) {
        em.removeEntity(toRemove);
    }

    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact <= requered / 2;
    }

    private int getFactNumberOfSpecificEntity(Object obj) {
        return getSpecificEntitiesByClass(obj).size();
    }

    // TODO воспользуйся поиском, поймешь чо тут делать
    public Map<Coordinate, Entity> getEntities() {
        return em.getEntities();
    }
}
