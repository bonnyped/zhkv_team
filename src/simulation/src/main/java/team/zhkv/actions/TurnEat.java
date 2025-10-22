package team.zhkv.actions;

import team.zhkv.map.GameManager;

public class TurnEat extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.eatAllEdibleEntities();
    }
}
