package team.zhkv.actions;

import team.zhkv.map.GameManager;

/**
 * Initialization action that spawns all entities at the start of the
 * simulation.
 *
 * @author bonnyped
 */
public class InitAllEntities extends Init {
    /**
     * Spawns all entities using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.spawnAllEntities();
    }
}
