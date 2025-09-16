package team.zhkv.actions;

import team.zhkv.entities.Location;
import team.zhkv.utils.PropertiesStorage;

public class InitAllEntities extends Init {
    private Map<Location, Entity> entities;
    private PropertiesStorage storage;privat

    public InitAllEntities(PropertiesStorage storage) {
        this.entities = entities;
        this.storage = storage;
    }

    @Override
    public void init() {
    };
}
