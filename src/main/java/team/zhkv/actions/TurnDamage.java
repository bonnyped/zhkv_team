package team.zhkv.actions;

import team.zhkv.map.GameManager;

/**
 * Turn action that handles damage for all damageable entities.
 *
 * @author bonnyped
 */
public class TurnDamage extends Turn {
    /**
     * Inflicts damage on all damageable entities using the provided GameManager.
     *
     * @param gm the GameManager instance
     */
    @Override
    public void action(GameManager gm) {
        gm.damageAllDamagebleEntities();
    }
}
