package team.zhkv.entities;

import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public interface Moveble {
    Location makeMove(GameMap gm, Location oldLocation);
}
