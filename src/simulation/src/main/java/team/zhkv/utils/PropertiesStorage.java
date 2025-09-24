package team.zhkv.utils;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Tree;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.App;

public class PropertiesStorage {
    private int maxEntitiesCount = App.FIELD_SIZE.getDx() * App.FIELD_SIZE.getDy() / 2;
    private int minHerbivorePopulation = maxEntitiesCount / 7;
    private int minGrassQuantity = minHerbivorePopulation;

    private Entity[] allEntities = { new Tree(maxEntitiesCount),
            new Rock(maxEntitiesCount),
            new Grass(maxEntitiesCount),
            new Herbivore(maxEntitiesCount),
            new Predator(maxEntitiesCount) };

    public Entity[] getAllEntities() {
        return allEntities;
    }

    public int getMinGrassQuantity() {
        return minGrassQuantity;
    }

}
