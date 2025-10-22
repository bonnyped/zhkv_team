package team.zhkv.core.interfaces;

// import team.zhkv.actions.move.Coordinate;

public interface IEater {
    void eat(IEdible edible);

    Class<? extends IEdible> getFood();

    // Coordinate getGoalCoordinate();
}
