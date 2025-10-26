package team.zhkv.actions;

import team.zhkv.map.GameManager;

/**
 * Turn action that respawns entities that are eligible for respawn.
 *
 * @author bonnyped
 */
public class TurnRespawn extends Turn {
    /**
     * Respawns entities using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.respawnEntities();
    }
}
