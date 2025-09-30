package team.zhkv.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.diogonunes.jcdp.color.api.Ansi.BColor;

import team.zhkv.App;
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
    private final int entitiesCount = App.FIELD_SIZE_MIN.getDy()
            * App.FIELD_SIZE_MIN.getDy() / 2 / 10;

    private List<Map<Location, Entity>> maps = new ArrayList<>();

    private LocationFabric locationFabric = new LocationFabric(maps);

    private EntityFabric entityFabric = new EntityFabric();

    private ChangeStorage cs = new ChangeStorage();

    private List<Class<? extends Entity>> entities = List.of(Tree.class,
            Rock.class,
            Grass.class,
            Herbivore.class,
            Predator.class);

    private int determIndexByCLass(Class<? extends Entity> clazz) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == clazz) {
                return i;
            }
        }
        return -1;
    }

    public void setAll() {
        if (maps.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                Map<Location, Entity> map = new HashMap<>();
                for (int j = 0; j < entitiesCount; j++) {
                    map.put(locationFabric.buildLocation(),
                            entityFabric.buildEntity(entities.get(i)));
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
        if (clazz == Tree.class) {
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

    public ChangeStorage getChangeStorage() {
        return cs;
    }

    public void applyChanges() {

    }
}
