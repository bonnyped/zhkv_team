package team.zhkv.actions;

import team.zhkv.render.EntitiesStorage;

public class InitAllEntities extends Init {
    @Override
    public void action(EntitiesStorage es) {
        es.setAll();
    }
}
