package team.zhkv.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEater;

public class GameManager {
    public static final int DX = 10;
    public static final int DY = 10;

    private final EntityManager em;
    private final CoordinateManager cm;
    private int turnCount;

    public GameManager() {
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

    public void spawnAllEntities() {
        for (Entity entity : em.createEntitiesToSpawn()) {
            safetyPutEntity(cm.getFreeCoordinate(), entity);
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

    public void removeEatedEntity(Coordinate toRemove) {
        em.removeEntity(toRemove);
    }

    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.getDx() >= 0
                && coordinate.getDx() < DX
                && coordinate.getDy() >= 0
                && coordinate.getDy() < DY;
    }

    public void buildPathToCreature(Coordinate src) {
        Creature creature = em.getCreature(src);

        if (creature != null) {
            cm.addActiveCoordinates(src, cm.buildPath(src, creature.getFood()),
                    creature.getSpeed());
        }
    }

    public void makeMoveForAllMovebleEntities() {
        em.moveAllEntities(cm.getMovebleEntitiesCoordinates());
    }

    public void damageAllDamagebleEntities() {
        for (var entry : em.collectEntitiesToActionByInterfaceClass(
                cm.getActionEntitiesCoordinates(),
                IDamager.class)) {
            entry.getKey().giveDamage(entry.getValue());
        }
    }

    public void eatAllEdibleEntities() {
        for (var entry : em.collectEntitiesToActionByInterfaceClass(
                cm.getActionEntitiesCoordinates(),
                IEater.class)) {
            entry.getKey().eat(entry.getValue());
        }
    }

    public void respawnEntities() {
        for (var entry : em.getRespawnableEntitiesAndCounts()) {
            int countToSpawn = getDiffCountsMinFact(
                    entry.getKey(), entry.getValue());
            for (int i = 0; i < countToSpawn; i++) {
                safetyPutEntity(cm.getFreeCoordinate(),
                        em.getNewEntity(entry.getKey()));
            }
        }
    }

    public void removeAllRemovable() {
        em.removeAllRemovable(cm.getActionEntitiesCoordinates());
    }

    public Map<Coordinate, Entity> getEntities() {
        return em.getEntities();
    }

    private void safetyPutEntity(Coordinate target, Entity entity) {
        if (!isOccupiedCoordinate(target)) {
            em.putEntity(target, entity);
        }
    }

    private int getDiffCountsMinFact(Class<? extends Entity> clazz,
            int requered) {
        int fact = getFactNumberOfEntity(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    private <T> int getFactNumberOfEntity(Class<T> clazz) {
        return collectSpecificEntities(clazz).size();
    }

    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact <= requered / 2;
    }

}
