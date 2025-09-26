package team.zhkv.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.EntityFabric;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.entities.Tree;
import team.zhkv.App;

public class EntitiesStorage {
    private final int entitiesCount = App.FIELD_SIZE_MIN.getDy()
            * App.FIELD_SIZE_MIN.getDy() / 2 / 10;

    private List<Map<Location, Entity>> maps = new ArrayList<>();

    private List<Class<? extends Entity>> entities = List.of(Tree.class,
            Rock.class,
            Grass.class,
            Herbivore.class,
            Predator.class);

    private LocationFabric locationFabric = new LocationFabric(maps);

    private EntityFabric entityFabric = new EntityFabric();

    private Map<Location, Entity> objectsToChange = new HashMap<>();

    private int determClassMapIndex(Class<? extends Entity> clazz) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == clazz) {
                return i;
            }
        }
        return -1;
    }

    public void setAll() {
        for (int i = 0; i < entities.size(); i++) {
            Map<Location, Entity> map = new HashMap<>();
            for (int j = 0; j < entitiesCount; j++) {
                map.put(locationFabric.buildLocation(),
                        entityFabric.buildEntity(entities.get(i)));
            }
            locationFabric.bindSetsWithMaps(map, entities.get(i));
            maps.add(map);
        }
    }

    public Location getNewLocation() {
        return locationFabric.buildLocation();
    }

    public int differenceEntityCountMinFact(Class<? extends Entity> clazz) {
        if (clazz == Tree.class) {
            return entitiesCount - locationFabric.getGrass().size();
        } else if (clazz == Herbivore.class) {
            return entitiesCount - locationFabric.getHerbivores().size();
        } else {
            return 0;
        }
    }

    public Map<Location, Entity> getObjectsToChange() {
        return objectsToChange;
    }

    public List<Map<Location, Entity>> getMaps() {
        return maps;
    }
}