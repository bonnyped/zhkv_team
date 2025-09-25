package team.zhkv.actions;

import team.zhkv.render.EntitiesStorage;

public class InitAllEntities extends Init {
    private EntitiesStorage es;

    public InitAllEntities(EntitiesStorage es) {
        this.es = es;
    }

    @Override
    public void init() {
        es.setAll();
    }
}
