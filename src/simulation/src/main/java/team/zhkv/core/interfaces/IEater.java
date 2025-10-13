package team.zhkv.service.impl;

import team.zhkv.entities.Entity;

public interface Eater {
    void eat(Edible Edible);

    Class<? extends Entity> getFood();
}
