package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.map.GameMap;

public class TurnDamage extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Creature> creatures = gm.getCreaturesMap();

            for (var creature : creatures.values()) {
                if (creature instanceof IDamager damager) {
                    IDamageble damagable = (IDamageble) creature.getGoal();
                    damager.giveDamage(damagable);
                }
            }
        }
    }
}
