package team.zhkv.actions;

import team.zhkv.map.GameManager;

public class TurnRespawn extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.respawnEntities();
    }
}
