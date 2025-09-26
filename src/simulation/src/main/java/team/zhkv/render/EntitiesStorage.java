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

    private LocationFabric locationFabric = new LocationFabric();

    private EntityFabric entityFabric = new EntityFabric();

    private void bindSetsWithMaps(Map<Location, Entity> map,
            Class<? extends Entity> clazz) {
        if (clazz == Grass.class) {
            locationFabric.setGrass(map.keySet());
        } else if (clazz == Herbivore.class) {
            locationFabric.setHerbivores(map.keySet());
        } else if (clazz == Predator.class) {
            locationFabric.setPredators(map.keySet());
        } else if (clazz == Tree.class) {
            locationFabric.setTrees(map.keySet());
        } else if (clazz == Rock.class) {
            locationFabric.setRokcs(map.keySet());
        }
    }

    public void setAll() {
        for (int i = 0; i < entities.size(); i++) {
            Map<Location, Entity> map = new HashMap<>();
            for (int j = 0; j < entitiesCount; j++) {
                map.put(locationFabric.buildLocation(),
                        entityFabric.buildEntity(entities.get(i)));
            }
            bindSetsWithMaps(map, entities.get(i));
            maps.add(map);
        }
    }
}