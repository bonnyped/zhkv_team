package team.zhkv.core.interfaces;

import team.zhkv.core.entities.Entity;

public interface IEater {
    void eat(Entity edible);

    Class<? extends IEdible> getFood();
}
