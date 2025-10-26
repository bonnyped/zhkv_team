package team.zhkv.actions;

import team.zhkv.map.GameManager;

/**
 * Turn action that handles eating for all edible entities.
 *
 * @author bonnyped
 */
public class TurnEat extends Turn {
    /**
     * Performs eating actions for all edible entities using the provided
     * GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.eatAllEdibleEntities();
    }
}
