package team.zhkv.entities;

import java.util.Map;

import team.zhkv.render.Location;

public interface Moveble {
    Map<Location, Entity> makeMove(Map<Location, Entity> oldCreaturesLocations,
            Location oldLocation);
}
