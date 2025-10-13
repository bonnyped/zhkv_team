package team.zhkv.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.actions.move.Coordinate;

public class EntityManager {
    private final Map<Class<? extends Entity>, Integer> maxCounts = Map.of(Tree.class, 5,
            Rock.class, 5,
            Grass.class, 5,
            Herbivore.class, 5,
            Predator.class, 5);
    private final Map<Coordinate, Entity> entities = new HashMap<>();
    private final EntityFactory ef = new EntityFactory();

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

    public void addEntity(Coordinate location, Entity entity) {
        if (isOccupied(location)) {
            throw new IllegalStateException("Coordinate is already occupied");
        }
        entities.put(location, entity);
    }

    public void removeEntity(Coordinate location) {
        entities.remove(location);
    }

    public Entity getEntity(Coordinate location) {
        return entities.get(location);
    }

    public boolean isOccupied(Coordinate location) {
        return entities.containsKey(location);
    }

    public List<Entity> getEntitiesAsList() {
        List<Entity> entitiesAsList = new ArrayList<>();

        for (var entry : maxCounts.entrySet()) {
            int count = entry.getValue();
            while (count > 0) {
                entitiesAsList.add(ef.buildEntity(entry.getKey()));
                --count;
            }
        }

        return entitiesAsList;
    }

    public Map<Coordinate, Entity> getCreaturesMap() {
        Map<Coordinate, Entity> creatures = new HashMap<>();
        for (var entry : entities.entrySet()) {
            if (entry.getValue() instanceof Creature creature) {
                creatures.put(entry.getKey(), creature);
            }
        }
        return creatures;
    }
}