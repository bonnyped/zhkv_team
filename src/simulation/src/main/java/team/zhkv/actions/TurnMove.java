package team.zhkv.actions;

import java.util.List;

import team.zhkv.core.entities.Creature;
import team.zhkv.map.GameManager;

public class TurnMove extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameManager.class) {
            GameManager gm = (GameManager) obj;
            List<Creature> creatures = gm.getSpecificEntitiesByClass(
                    Creature.class);

            for (Creature creature : creatures) {
                creature.makeMove();
            }
        }
    }
}
