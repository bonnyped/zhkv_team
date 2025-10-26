package team.zhkv.actions;

import team.zhkv.map.GameManager;

/**
 * Turn action that removes all removable entities from the game.
 *
 * @author bonnyped
 */
public class TurnRemove extends Turn {
    /**
     * Removes all removable entities using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.removeAllRemovable();
    }
}
