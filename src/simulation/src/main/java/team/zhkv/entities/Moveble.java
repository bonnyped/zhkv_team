package team.zhkv.entities;

import java.util.List;

import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public interface Moveble {
    List<Location> makeMove(GameMap gm, Location oldLocation);
}
