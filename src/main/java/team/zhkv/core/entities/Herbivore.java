package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IRespawnable;

/**
 * Represents a herbivore creature that can eat, take damage, and respawn.
 *
 * @author bonnyped
 */
public class Herbivore extends Creature<Herbivore> implements IEdible, IDamageble,
        IRespawnable {
    private boolean isEated;

    /**
     * Constructs a Herbivore with default parameters.
     */
    public Herbivore() {
        super(100, Grass.class, 2);
    }

    /**
     * Eats the specified entity if it is edible.
     *
     * @param entity the entity to eat
     */
    @Override
    public void eat(Entity entity) {
        if (entity instanceof IEdible edible) {
            edible.setEated();
        }
    }

    /**
     * Takes damage and reduces health points.
     *
     * @param damage the amount of damage
     * @return the remaining health
     */
    @Override
    public int takeDamage(int damage) {
        setHp(getHp() - damage);
        return getHp();
    }

    /**
     * Marks the herbivore as eaten.
     */
    @Override
    public void setEated() {
        isEated = true;
    }

    /**
     * Checks if the herbivore has been eaten.
     *
     * @return true if eaten, false otherwise
     */
    @Override
    public boolean isEaten() {
        return isEated;
    }
}
