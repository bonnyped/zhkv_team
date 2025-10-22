package team.zhkv.actions;

import team.zhkv.map.GameManager;

public class TurnMove extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.makeMoveForAllMovebleEntities();
    }
}
