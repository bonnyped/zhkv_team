package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.map.GameMap;

public class TurnDamage extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, IDamager> damagers = gm.getDamagersMap();

            for (var damager : damagers.values()) {
                if (gm.getEntity(damager.getGoalCoordinate()) instanceof IDamageble damagable) {
                    damager.giveDamage(damagable);
                }
            }
        }
    }
}
