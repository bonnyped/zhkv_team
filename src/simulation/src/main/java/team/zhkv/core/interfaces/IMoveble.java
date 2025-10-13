package team.zhkv.core.interfaces;

import team.zhkv.map.GameMap;
import team.zhkv.actions.move.Coordinate;

public interface IMoveble {
    Coordinate makeMove(GameMap gm, Coordinate oldLocation);
}
