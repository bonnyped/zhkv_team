package team.zhkv.actions;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;

public class InitSpawnEntities extends Init {
    private Map<Location, Entity> entities;

    public InitSpawnEntities(Map<Location, Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void execute() {

    };

    public boolean isSpawned() {
        return entities == null;
    }
}
