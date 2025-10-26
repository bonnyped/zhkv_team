package team.zhkv.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.actions.move.CoordinateManager;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.EntityManager;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEater;

/**
 * GameManager is responsible for managing the main game logic,
 * including entity management, turn progression, spawning, movement,
 * damage, eating, and respawn mechanics.
 *
 * @author bonnyped
 */
public class GameManager {
    /**
     * The width of the game board (number of columns).
     */
    public static final int DX = 50;

    /**
     * The height of the game board (number of rows).
     */
    public static final int DY = 20;

    private final EntityManager em;
    private final CoordinateManager cm;
    private int turnCount;

    /**
     * Constructs a new GameManager instance and initializes
     * the EntityManager and CoordinateManager.
     */
    public GameManager() {
        this.em = new EntityManager();
        this.cm = new CoordinateManager(this, em.collectOccupiedCoordinates());
        this.turnCount = 0;
    }

    /**
     * Returns the current turn count.
     *
     * @return the number of turns passed
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Increments the turn count by one.
     */
    public void incrementTurn() {
        ++turnCount;
    }

    /**
     * Spawns all entities at free coordinates.
     */
    public void spawnAllEntities() {
        for (Entity entity : em.createEntitiesToSpawn()) {
            safetyPutEntity(cm.getFreeCoordinate(), entity);
        }
    }

    /**
     * Returns the entity at the specified coordinate.
     *
     * @param coordinate the coordinate to query
     * @return the entity at the coordinate, or null if none exists
     */
    public Entity getEntity(Coordinate coordinate) {
        return em.getEntity(coordinate);
    }

    /**
     * Checks if the specified coordinate is occupied.
     *
     * @param coordinate the coordinate to check
     * @return true if occupied, false otherwise
     */
    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return cm.isOccupiedCoordinate(coordinate);
    }

    /**
     * Collects coordinates of entities of a specific class.
     *
     * @param clazz the class to filter entities by
     * @param <T>   the type of entity
     * @return a list of coordinates where entities of the specified class are
     *         located
     */
    public <T> List<Coordinate> collectSpecificEntities(Class<T> clazz) {
        List<Coordinate> specificEntities = new ArrayList<>();
        for (var entry : em.getEntities().entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                specificEntities.add(entry.getKey());
            }
        }
        return specificEntities;
    }

    /**
     * Checks if the coordinate is within the game bounds.
     *
     * @param coordinate the coordinate to check
     * @return true if in bounds, false otherwise
     */
    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.getDx() >= 0
                && coordinate.getDx() < DX
                && coordinate.getDy() >= 0
                && coordinate.getDy() < DY;
    }

    /**
     * Builds a path for the creature at the given coordinate.
     *
     * @param src the source coordinate of the creature
     */
    public void buildPathForCreature(Coordinate src) {
        Creature<?> creature = em.getCreature(src);

        if (creature != null) {
            cm.addActiveCoordinates(src, cm.buildPath(src, creature.getFood()),
                    creature.getSpeed());
        }
    }

    /**
     * Moves all movable entities according to their planned moves.
     */
    public void makeMoveForAllMovebleEntities() {
        em.moveAllEntities(cm.getMovebleEntitiesCoordinates());
        cm.clearToMoveMap();
    }

    /**
     * Applies damage from all damager entities to their targets.
     */
    public void damageAllDamagebleEntities() {
        for (var entry : em.collectEntitiesToActionByInterfaceClass(
                cm.getActionEntitiesCoordinates(),
                IDamager.class)) {
            entry.getKey().giveDamage(entry.getValue());
        }
    }

    /**
     * Handles eating actions for all eater entities.
     */
    public void eatAllEdibleEntities() {
        for (var entry : em.collectEntitiesToActionByInterfaceClass(
                cm.getActionEntitiesCoordinates(),
                IEater.class)) {
            entry.getKey().eat(entry.getValue());
        }
    }

    /**
     * Respawns entities according to their respawn rules.
     */
    public void respawnEntities() {
        Set<Entry<Class<? extends Entity>, Integer>> set = em.getRespawnableEntitiesAndCounts();
        for (var entry : set) {
            int countToSpawn = getDiffCountsMinFact(
                    entry.getKey(), entry.getValue());
            for (int i = 0; i < countToSpawn; i++) {
                safetyPutEntity(cm.getFreeCoordinate(),
                        em.getNewEntity(entry.getKey()));
            }
        }
    }

    /**
     * Removes all removable entities from the game.
     */
    public void removeAllRemovable() {
        em.removeAllRemovable(cm.getActionEntitiesCoordinates());
    }

    /**
     * Returns the map of all entities and their coordinates.
     *
     * @return a map of coordinates to entities
     */
    public Map<Coordinate, Entity> getEntities() {
        return em.getEntities();
    }

    /**
     * Safely puts an entity at the target coordinate if it is not already occupied.
     *
     * @param target the coordinate to place the entity
     * @param entity the entity to place
     */
    private void safetyPutEntity(Coordinate target, Entity entity) {
        if (!isOccupiedCoordinate(target)) {
            em.putEntity(target, entity);
        }
    }

    /**
     * Calculates the difference between required and actual entity count,
     * ensuring at least half of the required amount is present.
     *
     * @param clazz    the entity class
     * @param requered the required count
     * @return the number of entities to spawn
     */
    private int getDiffCountsMinFact(Class<? extends Entity> clazz,
            int requered) {
        int fact = getFactNumberOfEntity(clazz);
        return isFactLesserHalfRequered(fact, requered) ? requered - fact : 0;
    }

    /**
     * Returns the actual number of entities of the specified class.
     *
     * @param clazz the entity class
     * @param <T>   the type of entity
     * @return the number of entities of the specified class
     */
    private <T> int getFactNumberOfEntity(Class<T> clazz) {
        return collectSpecificEntities(clazz).size();
    }

    /**
     * Checks if the actual count is less than or equal to half of the required
     * count.
     *
     * @param fact     the actual count
     * @param requered the required count
     * @return true if actual is less than or equal to half of required, false
     *         otherwise
     */
    private boolean isFactLesserHalfRequered(int fact, int requered) {
        return fact <= requered / 2;
    }

}
