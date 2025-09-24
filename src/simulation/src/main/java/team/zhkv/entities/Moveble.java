package team.zhkv.entities;

import java.util.Map;
import java.util.Set;

import team.zhkv.render.Location;

public interface Moveble {
    void makeMove(Map<Location, Entity> locations,
            Location creatureLocation, Set<Location> newCreaturesLocations);
}
