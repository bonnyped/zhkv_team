package team.zhkv.entities;

import java.util.Set;

import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public interface Moveble {
    void makeMove(GameMap gm,
            Location creatureLocation, Set<Location> newCreaturesLocations);
}
