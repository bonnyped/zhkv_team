package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IEdible;

public class EntityManager {
    private final Map<Class<? extends Entity>, Integer> toSpawn = Map.of(Tree.class, 5,
            Rock.class, 5,
            Grass.class, 5,
            Herbivore.class, 5,
            Predator.class, 5);

    private final Map<Coordinate, Entity> entities = new HashMap<>();
    private final EntityFactory ef = new EntityFactory();

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    public void addEntity(Coordinate coordinate, Entity entity) {
        if (isOccupied(coordinate)) {
            throw new IllegalStateException("Coordinate is already occupied");
        }
        entities.put(coordinate, entity);
    }

    public void removeEntity(Coordinate coordinate) {
        entities.remove(coordinate);
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public boolean isOccupied(Coordinate coordinate) {
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

    public Map<Coordinate, Creature> getCreaturesMap() {
        Map<Coordinate, Creature> creatures = new HashMap<>();
        for (var entry : entities.entrySet()) {
            if (entry.getValue() instanceof Creature creature) {
                creatures.put(entry.getKey(), creature);
            }
        }
        return creatures;
    }

    public Map<Coordinate, IEdible> getEdiblesMap() {
        Map<Coordinate, IEdible> edibles = new HashMap<>();

        for (var entry : entities.entrySet()) {
            if (entry.getValue() instanceof IEdible edible) {
                edibles.put(entry.getKey(), edible);
            }
        }

        return edibles;
    }

    public Map<Class<? extends Entity>, Integer> getSpawnedEntities() {
        return toSpawn;
    }

    public Entity getNewEntity(Class<? extends Entity> clazz) {
        return ef.buildEntity(clazz);
    }

}