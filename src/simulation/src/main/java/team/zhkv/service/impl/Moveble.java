package team.zhkv.service.impl;

import team.zhkv.GameMap;
import team.zhkv.move.Location;

public interface Moveble {
    Location makeMove(GameMap gm, Location oldLocation);
}
