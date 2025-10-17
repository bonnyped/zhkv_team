package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IRespawnable;

public class EntityManager {
    private final Map<Class<? extends Entity>, Integer> toSpawn;
    private final Map<Coordinate, Entity> entities;
    private final EntityFactory ef;

    public EntityManager(Map<Coordinate, Entity> entities) {
        toSpawn = Map.of(Tree.class, 5,
                Rock.class, 5,
                Grass.class, 5,
                Herbivore.class, 5,
                Predator.class, 5);
        this.entities = entities;
        ef = new EntityFactory();
    }

    public Map<Coordinate, Entity> getEntities() {
        return entities;
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

    public Map<Coordinate, IEater> getEatersMap() {
        Map<Coordinate, IEater> edibles = new HashMap<>();

        for (var entry : entities.entrySet()) {
            if (entry.getValue() instanceof IEater eater) {
                edibles.put(entry.getKey(), eater);
            }
        }

        return edibles;
    }

    public Map<Coordinate, IDamager> getDamagersMap() {
        Map<Coordinate, IDamager> damager = new HashMap<>();

        return damager;
    }

    public Map<Class<? extends Entity>, Integer> getRespawnableEntities() {
        Map<Class<? extends Entity>, Integer> respawnableEntities = new HashMap<>();
        for (var entry : toSpawn.entrySet()) {
            if (IRespawnable.class.isAssignableFrom(entry.getKey())) {
                respawnableEntities.put(entry.getKey(), entry.getValue());
            }
        }
        return respawnableEntities;
    }

    public Entity getNewEntity(Class<? extends Entity> clazz) {
        return ef.buildEntity(clazz);
    }

}