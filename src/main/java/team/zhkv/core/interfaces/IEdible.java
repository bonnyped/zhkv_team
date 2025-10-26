package team.zhkv.core.interfaces;

/**
 * Represents an entity that can be eaten.
 *
 * @author bonnyped
 */
public interface IEdible {
    /**
     * Marks the entity as eaten.
     */
    void setEated();

    /**
     * Checks if the entity has been eaten.
     *
     * @return true if eaten, false otherwise
     */
    boolean isEaten();
}
