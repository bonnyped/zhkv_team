package team.zhkv.core.interfaces;

import team.zhkv.core.entities.Entity;

/**
 * Represents an entity capable of inflicting damage on others.
 *
 * @author bonnyped
 */
public interface IDamager {
    /**
     * Inflicts damage on the specified victim entity.
     *
     * @param victim the entity to damage
     */
    void giveDamage(Entity victim);
}
