package team.zhkv.entities;

import java.util.Map;
import java.util.Set;

import team.zhkv.render.GameMap;
import team.zhkv.render.Location;
import team.zhkv.utils.BFS;

public abstract class Creature extends Entity implements Moveble {
    protected Class<? extends Entity> food;

    protected Creature(int quatity) {
        super(quatity);
    }

    public Class<? extends Entity> getFood() {
        return food;
    }

    @Override
    public void makeMove(GameMap gm,
            Location creatureLocation, Set<Location> newCreaturesLocations) {
        Map<Location, Entity> locations = gm.getLocations();
        BFS bfs = new BFS(locations);
        Location newLocation = bfs.generateCreaturesStep(
                creatureLocation);
        if (newLocation != null) {
            gm.decrementEatable(newLocation);
            newCreaturesLocations.add(newLocation);
            locations.put(newLocation, locations.remove(creatureLocation));
        }
    }
}
