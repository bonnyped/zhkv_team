package team.zhkv.core.interfaces;

import team.zhkv.actions.move.Coordinate;

public interface IDamager {
    void giveDamage(IDamageble victim);

    Coordinate getGoalCoordinate();
}
