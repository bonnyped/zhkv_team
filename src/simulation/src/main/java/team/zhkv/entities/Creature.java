package team.zhkv.entities;

import java.util.Map;
import java.util.Set;

import team.zhkv.render.Location;
import team.zhkv.utils.BFS;

public abstract class Creature extends Entity implements Moveble {
    protected Class<? extends Entity> food;

    public Class<? extends Entity> getFood() {
        return food;
    }

    @Override
    public void makeMove(Map<Location, Entity> locations,
            Location creatureLocation, Set<Location> newCreaturesLocations) {
        BFS bfs = new BFS(locations);
        Location newLocation = bfs.generateCreaturesStep(
                creatureLocation);
        if (newLocation != null) {
            newCreaturesLocations.add(newLocation);
            locations.put(newLocation, locations.remove(creatureLocation));
        }
    }
}
