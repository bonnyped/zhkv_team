package team.zhkv.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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

    private ChangeStorage cs = new ChangeStorage();

    private LocationFabric locationFabric = new LocationFabric(maps, cs.getToCreate());

    private EntityFabric entityFabric = new EntityFabric();

    private List<Class<? extends Entity>> entities = List.of(Tree.class,
            Rock.class,
            Grass.class,
            Herbivore.class,
            Predator.class);

    public boolean checkDuplicate(Location location) {
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).containsKey(location)) {
                return true;
            }
        }
        return false;
    }

    public void setAll() {
        if (maps.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                Map<Location, Entity> map = new HashMap<>();
                for (int j = 0; j < entitiesCount; j++) {
                    Location location = locationFabric.buildLocation();
                    map.put(location,
                            entityFabric.buildEntity(entities.get(i)));
                    if (checkDuplicate(location)) {
                        System.out.println(
                                "Duplicate " + entities.get(i).toString() + " in Location " + location.toString());
                    }
                }
                maps.add(map);
            }
        }
    }

    public Map<Location, Entity> getMapByEntity(Class<? extends Entity> clazz) {
        return maps.get(determIndexByCLass(clazz));
    }

    public Location getNewLocation() {
        return locationFabric.buildLocation();
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

    public Map<Location, Entity> getStorageToCreate() {
        return cs.getToCreate();
    }

    public Map<Location, Location> getToMoveStorage() {
        return cs.getToMove();
    }

    public Set<Location> getToRemoveStorage() {
        return cs.getToRemove();
    }

    public void applyChanges() {
        createInitedEntities();
        removeEntities();
        moveCreatures();
    }

    public void applyMove(Location src, Location target) {
        for (var map : maps) {
            if (map.containsKey(src)) {
                map.put(target, map.get(src));
                if (checkDuplicate(target)) {
                    System.out.println("Duplicate " + map.get(src).getClass() + " in Location " + target.toString());
                }
            }
        }
    }

    private int determIndexByCLass(Class<? extends Entity> clazz) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == clazz) {
                return i;
            }
        }
        return -1;
    }

    private void createInitedEntities() {
        Iterator<Entry<Location, Entity>> it = cs.getToCreate()
                .entrySet()
                .iterator();

        while (it.hasNext()) {
            var entry = it.next();
            maps.get(determIndexByCLass(entry.getValue().getClass()))
                    .put(entry.getKey(), entry.getValue());
            it.remove();
        }
    }

    private void removeEntities() {
        for (var deletingLocation : cs.getToRemove()) {
            for (var map : maps) {
                map.remove(deletingLocation);
            }
        }
        cs.getToRemove().clear();
    }

    public void moveCreatures() {
        for (var stepFromTo : cs.getToMove().entrySet()) {
            for (int i = 0; i < maps.size(); i++) {
                var map = maps.get(i);
                if (map.containsKey(stepFromTo.getKey())) {
                    if (checkDuplicate(stepFromTo.getValue())) {
                        System.out.println("Duplicate " + map.get(stepFromTo.getKey()) + " in Location "
                                + stepFromTo.getValue().toString());
                    }
                    map.put(stepFromTo.getValue(),
                            map.remove(stepFromTo.getKey()));
                    break;
                }
            }
        }
    }

}
