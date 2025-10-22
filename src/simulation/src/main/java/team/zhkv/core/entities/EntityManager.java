package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEdible;

public class EntityManager {
    private final Map<Coordinate, Entity> entities;

    private final Map<Class<? extends Entity>, Integer> toSpawn;
    private final EntityFactory ef;

    public EntityManager() {
        entities = new HashMap<>();
        toSpawn = Map.of(Tree.class, 10,
                Rock.class, 6,
                Grass.class, 6,
                Herbivore.class, 6,
                Predator.class, 6);
        ef = new EntityFactory();
    }

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    public Entity getNewEntity(Class<? extends Entity> clazz) {
        return ef.buildEntity(clazz);
    }

    public void putEntity(Coordinate coordinate, Entity entity) {
        entities.put(coordinate, entity);
    }

    public List<Entity> createEntitiesToSpawn() {
        List<Entity> entitiesAsList = new ArrayList<>();

        for (var entry : toSpawn.entrySet()) {
            int count = entry.getValue();
            while (count > 0) {
                entitiesAsList.add(ef.buildEntity(entry.getKey()));
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

    public Collection<Entity> getValues() {
        return entities.values();
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
            entities.put(entry.getValue(), entities.remove(entry.getKey()));
        }
    }

    public <T, V> void makeActionAllEntities(
            Map<Coordinate, Coordinate> entitiesToAction,
            Class<T> active,
            Class<V> passive) {
        collectEntriesBySpecificInterfaces(entitiesToAction, passive)
                .entrySet()
                .stream()
                .forEach(entry -> {
                    if (entities.get(
                            entry.getKey()) instanceof IDamager damager) {
                        damager.giveDamage(entities.get(entry.getValue()));
                    }
                });
    }

    private <T> Map<Coordinate, Coordinate> collectEntriesBySpecificInterfaces(
            Map<Coordinate, Coordinate> entitiesToAction, Class<T> clazz) {
        return entitiesToAction.entrySet()
                .stream()
                .filter(entry -> clazz.isInstance(entities.get(entry.getValue())))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
    }
}