package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEdible;

/**
 * Represents a predator creature that can inflict damage and eat other
 * entities.
 *
 * @author bonnyped
 */
public class Predator extends Creature<Predator> implements IDamager {
    private int damagePower;

    /**
     * Constructs a Predator with default parameters.
     */
    public Predator() {
        super(100, Herbivore.class, 1);
    }

    /**
     * Inflicts damage on the specified victim entity.
     *
     * @param victim the entity to damage
     */
    @Override
    public void giveDamage(Entity victim) {
        if (victim instanceof IDamageble damageble) {
            damageble.takeDamage(damagePower);
        }
    }

    /**
     * Eats the specified entity if it is edible and a creature.
     *
     * @param entity the entity to eat
     */
    @Override
    public void eat(Entity entity) {
        if (entity instanceof IEdible edible
                && entity instanceof Creature) {
            edible.setEated();
        }
    }

    /**
     * Sets the damage power and returns this predator.
     *
     * @param damagePower the damage power
     * @return this predator
     */
    public Predator withDamage(int damagePower) {
        this.damagePower = damagePower;
        return this;
    }
}
