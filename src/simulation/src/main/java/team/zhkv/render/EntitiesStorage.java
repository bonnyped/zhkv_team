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

    private Map<Location, Entity> grass;
    private Map<Location, Entity> herbivores;
    private Map<Location, Entity> predators;

    private List<Class<? extends Entity>> entities = List.of(Tree.class,
            Rock.class,
            Grass.class,
            Herbivore.class,
            Predator.class);

    private LocationFabric locationFabric = new LocationFabric();

    private EntityFabric entityFabric = new EntityFabric();

    private void defineChangebleEntities(Map<Location, Entity> map,
            Class<? extends Entity> clazz) {
        if (clazz == Grass.class) {
            grass = map;
        } else if (clazz == Herbivore.class) {
            herbivores = map;
        } else if (clazz == Predator.class) {
            predators = map;
        }
    }

    public void setAll() {
        for (int i = 0; i < entities.size(); i++) {
            Map<Location, Entity> map = new HashMap<>();
            for (int j = 0; j < entitiesCount; j++) {
                map.put(locationFabric.buildLocation(),
                        entityFabric.buildEntity(entities.get(i)));
            }
            defineChangebleEntities(map, entities.get(i));
            maps.add(map);
        }
    }

    public Map<Location, Entity> getPredators() {
        return predators;
    }

    public void setPredators(Map<Location, Entity> predators) {
        this.predators = predators;
    }

    public Map<Location, Entity> getHerbivores() {
        return herbivores;
    }

    public void setHerbivores(Map<Location, Entity> herbivores) {
        this.herbivores = herbivores;
    }

    public Map<Location, Entity> getGrass() {
        return grass;
    }

}