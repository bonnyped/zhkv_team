package team.zhkv.entities;

import java.util.Map;

import team.zhkv.render.Location;

public interface Moveble {
    void makeMove(Map<Location, Entity> oldCreaturesLocations,
            Location oldLocation, Map<Location, Entity> entitiesToRemove,
            Map<Location, Entity> newCreaturesLocations);
}
