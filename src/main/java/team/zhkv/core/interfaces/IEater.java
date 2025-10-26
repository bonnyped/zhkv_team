package team.zhkv.core.interfaces;

import team.zhkv.core.entities.Entity;

/**
 * Represents an entity capable of eating other entities.
 *
 * @author bonnyped
 */
public interface IEater {
    /**
     * Eats the specified edible entity.
     *
     * @param edible the entity to eat
     */
    void eat(Entity edible);

    /**
     * Returns the class of edible entities this eater can consume.
     *
     * @return the class of edible entities
     */
    Class<? extends IEdible> getFood();
}
