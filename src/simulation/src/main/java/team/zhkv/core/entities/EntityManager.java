package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;

public class EntityManager {
    private final Map<Class<? extends Entity>, Integer> toSpawn;
    private final Map<Coordinate, Entity> entities;
    private final EntityFactory ef;

    public EntityManager(Map<Coordinate, Entity> entities) {
        toSpawn = Map.of(Tree.class, 10,
                Rock.class, 6,
                Grass.class, 6,
                Herbivore.class, 6,
                Predator.class, 6);
        this.entities = entities;
        ef = new EntityFactory();
    }

    public Entity getNewEntity(Class<? extends Entity> clazz) {
        return ef.buildEntity(clazz);
    }

    public void putEntity(Coordinate coordinate, Entity entity) {
        if (isOccupiedCoordinate(coordinate)) {
            throw new IllegalStateException("Coordinate is already occupied");
        }
        entities.put(coordinate, entity);
    }

    public Entity removeEntity(Coordinate coordinate) {
        return entities.remove(coordinate);
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return entities.containsKey(coordinate);
    }

    public List<Entity> getEntitiesAsList() {
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

    public <T> List<T> getSpecificEntitiesByClass(Class<T> clazz) {
        List<T> specificEntities = new ArrayList<>();

        for (var entity : entities.values()) {
            if (clazz.isInstance(entity)) {
                specificEntities.add(clazz.cast(entity));
            }
        }

        return specificEntities;
    }

    // TODO заменить прямой доступ к сущностям
    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }
}