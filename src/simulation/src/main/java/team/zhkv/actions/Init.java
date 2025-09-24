package team.zhkv.actions;

import team.zhkv.App;
import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public abstract class Init implements Action {
    public abstract void init(GameMap gm);

    public void createIntities(GameMap gm, Entity entity) {
        Location randomUniqueLocation = new Location()
                .getFreeRandomLocation(App.FIELD_SIZE_MIN,
                        gm.getLocations());
        gm.getLocations().put(randomUniqueLocation, entity);
        if (entity instanceof Creature) {
            gm.getCreatures().add(randomUniqueLocation);
        }
    }
}
