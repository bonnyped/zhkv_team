package team.zhkv.service.impl;

import team.zhkv.GameMap;
import team.zhkv.move.Coordinate;

public interface Moveble {
    Coordinate makeMove(GameMap gm, Coordinate oldLocation);
}
