package team.zhkv.map;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.*;

public class EntityManager {
    private final Map<Class<? extends Entity>, Integer> MAX_COUNTS = Map.of(Tree.class, 5,
            Rock.class, 5,
            Grass.class, 5,
            Herbivore.class, 5,
            Predator.class, 5);
    private final Map<Coordinate, Entity> entities;
    private final EntityFactory ef = new EntityFactory();

    public EntityManager(Map<Coordinate, Entity> entities) {
        this.entities = entities;
    }

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
        return MAX_COUNTS.entrySet().stream()
                .flatMap(entry -> IntStream.range(0, entry.getValue())
                        .mapToObj(i -> ef.buildEntity(entry.getKey())))
                .collect(Collectors.toList());
    }
}