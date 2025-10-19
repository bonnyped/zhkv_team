package team.zhkv.core.interfaces;

import team.zhkv.actions.move.Coordinate;

public interface IEater {
    void eat(IEdible edible);

    IEdible getFood();

    Coordinate getGoalCoordinate();
}
