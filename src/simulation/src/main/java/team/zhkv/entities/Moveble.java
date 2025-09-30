package team.zhkv.entities;

import java.util.Map;

import team.zhkv.move.Location;
import team.zhkv.render.ChangeStorage;

public interface Moveble {
    void makeMove(Map<Location, Entity> oldCreaturesLocations,
            Location oldLocation, ChangeStorage cs);
}
