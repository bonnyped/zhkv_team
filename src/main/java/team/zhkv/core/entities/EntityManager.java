package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.Collection;
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

public class EntityManager {
    private final Map<Coordinate, Entity> entities;

    private final EntityBuilder eb;
    private final Map<Class<? extends Entity>, Integer> toSpawn;

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

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    public Entity getNewEntity(Class<? extends Entity> clazz) {
        return eb.buildEntity(clazz);
    }

    public void putEntity(Coordinate coordinate, Entity entity) {
        entities.put(coordinate, entity);
    }

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

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public Entity removeEntity(Coordinate coordinate) {
        return entities.remove(coordinate);
    }

    public Set<Coordinate> collectOccupiedCoordinates() {
        return entities.keySet();
    }

    public Creature getCreature(Coordinate coordinate) {
        if (entities.get(coordinate) instanceof Creature creature) {
            return creature;
        } else {
            return null;
        }
    }

    public void moveAllEntities(Map<Coordinate, Coordinate> movebleEntities) {
        for (var entry : movebleEntities.entrySet()) {
            if (!entities.containsKey(entry.getValue())) {
                putEntity(entry.getValue(), entities.remove(entry.getKey()));
            }
        }
    }

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

    public Set<Map.Entry<Class<? extends Entity>, Integer>> getRespawnableEntitiesAndCounts() {
        Set<Map.Entry<Class<? extends Entity>, Integer>> respawnable = new HashSet<>();

        for (var entry : toSpawn.entrySet()) {
            if (IRespawnable.class.isAssignableFrom(entry.getKey())) {
                respawnable.add(entry);
            }
        }

        return respawnable;
    }

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