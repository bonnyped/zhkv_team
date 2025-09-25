package team.zhkv.actions;

import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;

public class InitAllEntities extends Init {
    private boolean isInited;

    public InitAllEntities(GameMap gm) {
        super.gm = gm;
    }

    @Override
    public void init() {
        if (!isInited) {
            Entity[] entities = gm.getAllEntities();
            for (int i = 0; i < entities.length; i++) {
                for (int j = 0; j < entities[i].getQuantity(); j++) {
                    createIntities(gm, entities[i]);
                }
            }
            isInited = true;
        }
    }
}
