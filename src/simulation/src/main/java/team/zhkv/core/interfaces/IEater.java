package team.zhkv.core.interfaces;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Entity;

public interface IEater {
    void eat(IEdible edible);

    Class<? extends Entity> getFood();

    Coordinate getGoalCoordinate();
}
