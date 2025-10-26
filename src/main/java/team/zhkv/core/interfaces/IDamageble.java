package team.zhkv.core.interfaces;

/**
 * Represents an entity that can take damage.
 *
 * @author bonnyped
 */
public interface IDamageble {
    /**
     * Applies damage to the entity.
     *
     * @param damage the amount of damage to apply
     * @return the remaining health after taking damage
     */
    int takeDamage(int damage);
}
