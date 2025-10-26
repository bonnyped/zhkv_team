package team.zhkv.actions;

import team.zhkv.map.GameManager;
import team.zhkv.core.entities.Creature;

/**
 * Turn action that builds paths for all creatures in the game.
 *
 * @author bonnyped
 */
public class TurnPathfinder extends Turn {
    /**
     * Builds paths for all creatures using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.collectSpecificEntities(Creature.class)
                .stream()
                .forEach(gm::buildPathForCreature);
    }
}
