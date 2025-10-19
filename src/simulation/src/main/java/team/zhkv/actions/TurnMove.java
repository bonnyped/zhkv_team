package team.zhkv.actions;

import team.zhkv.core.entities.Creature;
import team.zhkv.map.GameManager;

public class TurnMove extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.collectSpecificEntities(Creature.class).stream()
                .forEach(Creature::makeMove);
    }
}
