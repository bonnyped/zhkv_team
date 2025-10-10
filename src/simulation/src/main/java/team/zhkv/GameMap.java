package team.zhkv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.entities.Tree;
import team.zhkv.move.Location;
import team.zhkv.service.EntityFabric;
import team.zhkv.service.LocationFabric;

public class GameMap {
    public static final Location DEFAULT_FIELD_SIZE = new Location(
            10, 30);
    private Location customFieldSize;
    private final Location fieldSize = customFieldSize == null
            ? DEFAULT_FIELD_SIZE
            : customFieldSize;

    public Location getFieldSize() {
        return fieldSize;
    }

    private final int entitiesCount = fieldSize.getDx()
            * fieldSize.getDy() / 2 / 20;

    private List<Map<Location, Entity>> maps = new ArrayList<>();

    private Map<Location, Location> moves = new HashMap<>();

    private LocationFabric locationFabric = new LocationFabric(maps, fieldSize);

    private EntityFabric entityFabric = new EntityFabric();

    private List<Class<? extends Entity>> entities = List.of(Tree.class,
            Rock.class,
            Grass.class,
            Herbivore.class,
            Predator.class);

    public GameMap() {
    }

    public GameMap(int x, int y) {
        customFieldSize = new Location(x, y);
    }

    public Map<Location, Location> getMoves() {
        return moves;
    }

    public void setMoves(Map<Location, Location> moves) {
        this.moves = moves;
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
