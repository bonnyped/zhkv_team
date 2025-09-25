package team.zhkv.utils;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Tree;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.App;

public class PropertiesStorage {
    private int maxEntitiesCount = App.FIELD_SIZE_MIN.getDx() * App.FIELD_SIZE_MIN.getDy() / 2;

    private Entity[] allEntities = { new Tree(maxEntitiesCount / 8),
            new Rock(maxEntitiesCount / 8),
            new Grass(maxEntitiesCount / 5),
            new Herbivore(maxEntitiesCount / 5),
            new Predator(maxEntitiesCount / 5) };

    public Entity[] getAllEntities() {
        return allEntities;
    }
}
