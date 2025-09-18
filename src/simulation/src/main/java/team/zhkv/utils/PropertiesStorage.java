package team.zhkv.utils;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Tree;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;

public class PropertiesStorage {
    private int sizex = 10;
    private int sizey = 10;

    public int getSizex() {
        return this.sizex;
    }

    public int getSizey() {
        return this.sizey;
    }

    private int maxEntitiesCount = sizex * sizey / 2;
    private int minHerbivorePopulation = maxEntitiesCount / 7;
    private int minGrassQuantity = minHerbivorePopulation;

    private Entity[] entities = { new Tree(maxEntitiesCount),
            new Rock(maxEntitiesCount),
            new Grass(maxEntitiesCount),
            new Herbivore(maxEntitiesCount),
            new Predator(maxEntitiesCount) };

    public Entity[] getEntities() {
        return entities;
    }
}
