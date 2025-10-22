package team.zhkv.actions;

import team.zhkv.map.GameManager;
import team.zhkv.core.entities.Creature;

public class TurnPathfinder extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.collectSpecificEntities(Creature.class)
                .stream()
                .forEach(gm::buildPathToCreature);
    }
}
