package team.zhkv.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.EntityFabric;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.entities.Tree;
import team.zhkv.move.Location;
import team.zhkv.move.LocationFabric;

public class GameMap {
    public static final Location FIELD_SIZE_MIN = new Location(5, 5);
    public static final Location FIELD_SIZE_MID = new Location(10, 10);
    public static final Location FIELD_SIZE_MAX = new Location(77, 77);

    private final int entitiesCount = FIELD_SIZE_MID.getDy()
            * FIELD_SIZE_MID.getDy() / 2 / 10;

    private List<Map<Location, Entity>> maps = new ArrayList<>();

    private List<List<Location>> pathsStorage = new ArrayList<>();

    private LocationFabric locationFabric = new LocationFabric(maps);

    private EntityFabric entityFabric = new EntityFabric();

    private List<Class<? extends Entity>> entities = List.of(Tree.class,
            Rock.class,
            Grass.class,
            Herbivore.class,
            Predator.class);

    public List<List<Location>> getPathsStorage() {
        return pathsStorage;
    }

    public Entity getEntity(Location location) {
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).containsKey(location)) {
                return maps.get(i).get(location);
            }
        }
        return null;
    }

    public Entity removeEntity(Location location) {
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).containsKey(location)) {
                return maps.get(i).remove(location);
            }
        }
        return null;
    }

    public void setAll() {
        if (maps.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                Map<Location, Entity> map = new HashMap<>();
                for (int j = 0; j < entitiesCount; j++) {
                    Location location = locationFabric.buildLocation();
                    map.put(location,
                            entityFabric.buildEntity(entities.get(i)));
                }
                locationFabric.clearBuildedLocations();
                maps.add(map);
            }
        }
    }

    public Map<Location, Entity> getMapByEntity(Class<? extends Entity> clazz) {
        return maps.get(determIndexByCLass(clazz));
    }

    public Location getNewLocation() {
        Location newLocation = locationFabric.buildLocation();
        locationFabric.clearBuildedLocations();
        return newLocation;
    }

    public int differenceEntityCountMinFact(Class<? extends Entity> clazz) {
        if (clazz == Grass.class) {
            return entitiesCount - getMapByEntity(clazz).size();
        } else if (clazz == Herbivore.class) {
            return entitiesCount - getMapByEntity(clazz).size();
        } else {
            return 0;
        }
    }

    public Map<Location, Entity> getWholeMapEntities() {
        return maps.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Map<Location, Entity>> getCreaturesMaps() {
        List<Map<Location, Entity>> creatures = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            if (Creature.class.isAssignableFrom(entities.get(i))) {
                creatures.add(maps.get(i));
            }
        }
        return creatures;
    }

    private int determIndexByCLass(Class<? extends Entity> clazz) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == clazz) {
                return i;
            }
        }
        return -1;
    }
}
