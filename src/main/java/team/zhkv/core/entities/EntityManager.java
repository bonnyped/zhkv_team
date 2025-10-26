package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IRespawnable;

/**
 * Manages all entities in the game, including creation, placement, movement,
 * and removal.
 *
 * @author bonnyped
 */
public class EntityManager {
    private final Map<Coordinate, Entity> entities;
    private final EntityBuilder eb;
    private final Map<Class<? extends Entity>, Integer> toSpawn;

    /**
     * Constructs an EntityManager and initializes entity spawn counts.
     */
    public EntityManager() {
        eb = new EntityBuilder();
        entities = new HashMap<>();
        Map<Class<? extends Entity>, Integer> spawn = new LinkedHashMap<>();
        spawn.put(Tree.class, 10);
        spawn.put(Rock.class, 9);
        spawn.put(Grass.class, 7);
        spawn.put(Herbivore.class, 7);
        spawn.put(Predator.class, 5);
        toSpawn = Collections.unmodifiableMap(spawn);
    }

    /**
     * Returns the map of coordinates to entities.
     *
     * @return the entities map
     */
    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    /**
     * Creates a new entity of the specified class.
     *
     * @param clazz the class of the entity
     * @return the created entity
     */
    public Entity getNewEntity(Class<? extends Entity> clazz) {
        return eb.buildEntity(clazz);
    }

    /**
     * Places an entity at the specified coordinate.
     *
     * @param coordinate the coordinate
     * @param entity     the entity to place
     */
    public void putEntity(Coordinate coordinate, Entity entity) {
        entities.put(coordinate, entity);
    }

    /**
     * Creates a list of entities to spawn based on initial counts.
     *
     * @return the list of entities to spawn
     */
    public List<Entity> createEntitiesToSpawn() {
        List<Entity> entitiesAsList = new ArrayList<>();

        for (var entry : toSpawn.entrySet()) {
            int count = entry.getValue();
            while (count > 0) {
                entitiesAsList.add(eb.buildEntity(entry.getKey()));
                --count;
            }
        }

        return entitiesAsList;
    }

    /**
     * Returns the entity at the specified coordinate.
     *
     * @param coordinate the coordinate
     * @return the entity, or null if none exists
     */
    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    /**
     * Removes and returns the entity at the specified coordinate.
     *
     * @param coordinate the coordinate
     * @return the removed entity, or null if none existed
     */
    public Entity removeEntity(Coordinate coordinate) {
        return entities.remove(coordinate);
    }

    /**
     * Returns a set of all occupied coordinates.
     *
     * @return the set of occupied coordinates
     */
    public Set<Coordinate> collectOccupiedCoordinates() {
        return entities.keySet();
    }

    /**
     * Returns the creature at the specified coordinate, or null if not a creature.
     *
     * @param coordinate the coordinate
     * @return the creature, or null
     */
    public Creature getCreature(Coordinate coordinate) {
        if (entities.get(coordinate) instanceof Creature<?> creature) {
            return creature;
        } else {
            return null;
        }
    }

    /**
     * Moves all entities according to the provided movement map.
     *
     * @param movebleEntities map of source to target coordinates
     */
    public void moveAllEntities(Map<Coordinate, Coordinate> movebleEntities) {
        for (var entry : movebleEntities.entrySet()) {
            if (!entities.containsKey(entry.getValue())) {
                putEntity(entry.getValue(), entities.remove(entry.getKey()));
            }
        }
    }

    /**
     * Collects entities for action based on interface class.
     *
     * @param <T>         the type of the interface
     * @param toActionMap map of source to target coordinates
     * @param clazzT      the interface class
     * @return set of entries mapping interface instances to entities
     */
    public <T> Set<Map.Entry<T, Entity>> collectEntitiesToActionByInterfaceClass(
            Map<Coordinate, Coordinate> toActionMap,
            Class<T> clazzT) {
        Map<T, Entity> collected = new HashMap<>();

        for (var entry : toActionMap.entrySet()) {
            T active = getClazzAssignableFrom(clazzT, entry.getKey());
            Entity passive = entities.get(entry.getValue());
            if (active != null) {
                collected.put(active, passive);
            }
        }

        return collected.entrySet();
    }

    /**
     * Returns a set of entity types that can respawn and their required counts.
     *
     * @return a set of entries where the key is the respawnable entity class and
     *         the value is the required count
     */
    public Set<Map.Entry<Class<? extends Entity>, Integer>> getRespawnableEntitiesAndCounts() {
        Set<Map.Entry<Class<? extends Entity>, Integer>> respawnable = new HashSet<>();

        for (var entry : toSpawn.entrySet()) {
            if (IRespawnable.class.isAssignableFrom(entry.getKey())) {
                respawnable.add(entry);
            }
        }

        return respawnable;
    }

    /**
     * Removes all entities that are edible and have been eaten.
     *
     * @param toAction map of source to target coordinates
     */
    public void removeAllRemovable(Map<Coordinate, Coordinate> toAction) {
        for (var entry : toAction.entrySet()) {
            if (entities.get(entry.getValue()) instanceof IEdible edible
                    && edible.isEaten()) {
                removeEntity(entry.getValue());
            }
        }
    }

    private <T> T getClazzAssignableFrom(Class<T> clazz, Coordinate coordinate) {
        Object obj = entities.get(coordinate);
        return obj != null && clazz.isInstance(obj)
                ? clazz.cast(obj)
                : null;
    }
}