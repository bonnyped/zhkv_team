package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IRespawnable;

/**
 * Represents a grass entity that can be eaten and respawned.
 *
 * @author bonnyped
 */
public class Grass extends Entity implements IEdible, IRespawnable {
    private boolean isEated;

    /**
     * Checks if the grass has been eaten.
     *
     * @return true if eaten, false otherwise
     */
    @Override
    public boolean isEaten() {
        return isEated;
    }

    /**
     * Marks the grass as eaten.
     */
    @Override
    public void setEated() {
        isEated = true;
    }
}
