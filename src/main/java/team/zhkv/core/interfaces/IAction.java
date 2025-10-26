package team.zhkv.core.interfaces;

import team.zhkv.map.GameManager;

/**
 * Represents an action that can be performed in the game simulation.
 *
 * @author bonnyped
 */
public interface IAction {
    /**
     * Performs the action using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    void action(GameManager gm);
}
