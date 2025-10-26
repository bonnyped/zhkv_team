package team.zhkv.actions;

import team.zhkv.map.GameManager;

/**
 * Turn action that handles entity movement for all movable entities.
 *
 * @author bonnyped
 */
public class TurnMove extends Turn {
    /**
     * Makes a move for all movable entities using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.makeMoveForAllMovebleEntities();
    }
}
