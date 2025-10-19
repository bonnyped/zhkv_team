package team.zhkv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;
import team.zhkv.core.interfaces.IEdible;

public class GameManager {
    public static final int DX = 50;
    public static final int DY = 20;

    private final Map<Coordinate, Coordinate> toAction;
    private final EntityManager em;
    private final CoordinateManager cm;
    private int turnCount;

    public GameManager() {
        this.toAction = new HashMap<>();
        this.em = new EntityManager();
        this.cm = new CoordinateManager(this, em.collectOccupiedCoordinates());
        this.turnCount = 0;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void incrementTurn() {
        ++turnCount;
    }

    public Entity getEntityToSpawn(Class<? extends Entity> clazz) {
        return em.getNewEntity(clazz);
    }

    public void spawnAllEntities() {
        for (Entity entity : em.createEntitiesToSpawn()) {
            Coordinate newCoordinate = cm.getFreeCoordinate();
            safetyPutEntity(newCoordinate, entity);
        }
    }

    public Entity getEntity(Coordinate coordinate) {
        return em.getEntity(coordinate);
    }

    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return cm.isOccupiedCoordinate(coordinate);
    }

    public void updateEntityCoordinate(Coordinate src, Coordinate target) {
        Entity entity = em.getEntity(src);
        safetyPutEntity(target, entity);
    }

    public <T> List<Coordinate> collectSpecificEntities(Class<T> clazz) {
        List<Coordinate> specificEntities = new ArrayList<>();
        for (var entry : em.getEntities().entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                specificEntities.add(entry.getKey());
            }
        }
        return specificEntities;
    }

    public Coordinate getFreeCoordinate() {
        return cm.getFreeCoordinate();
    }

    // THIS getDiffCountsMinFact
    public int getDiffCountsMinFact(Class<? extends Entity> clazz,
            int requered) {
        int fact = getFactNumberOfEntity(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    public void removeEatedEntity(Coordinate toRemove) {
        em.removeEntity(toRemove);
    }

    public void buildPath(Coordinate src) {
        List<Coordinate> path;
        Coordinate target;
        IEdible edible = em.getCreaturesFood(src);
        
        if (edible != null) {
            path = cm.buildPath(src, edible);
            target = determTargetCoordinate();
            toAction.put(src, );
        }
    }

    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.getDx() >= 0
                && coordinate.getDx() < DX
                && coordinate.getDy() >= 0
                && coordinate.getDy() < DY;
    }

    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact <= requered / 2;
    }

    private <T> int getFactNumberOfEntity(Class<T> clazz) {
        return collectSpecificEntities(clazz).size();
    }

    private void safetyPutEntity(Coordinate target, Entity entity) {
        if (!isOccupiedCoordinate(target)) {
            em.putEntity(target, entity);
        }
    }
}
