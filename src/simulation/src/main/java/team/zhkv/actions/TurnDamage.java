package team.zhkv.actions;

import team.zhkv.map.GameManager;

public class TurnDamage extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.damageAllDamagebleEntities();
    }
}
