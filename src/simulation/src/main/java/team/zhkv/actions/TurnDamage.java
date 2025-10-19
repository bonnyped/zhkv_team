package team.zhkv.actions;

import java.util.List;

import team.zhkv.core.entities.Entity;
import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.map.GameManager;

public class TurnDamage extends Turn {
    @Override
    public void action(GameManager gm) {
        List<IDamager> damagers = gm.collectSpecificEntities(IDamager.class);

        for (var damager : damagers) {
            Entity goalEntity = gm.getEntity(damager.getGoalCoordinate());
            if (goalEntity != null
                    && goalEntity instanceof IDamageble damagable) {
                damager.giveDamage(damagable);
            }
        }
    }
}
