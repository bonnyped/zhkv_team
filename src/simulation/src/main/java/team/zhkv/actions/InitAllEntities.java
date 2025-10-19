package team.zhkv.actions;

import team.zhkv.map.GameManager;

public class InitAllEntities extends Init {
    @Override
    public void action(GameManager gm) {
        gm.spawnAllEntities();
    }
}
