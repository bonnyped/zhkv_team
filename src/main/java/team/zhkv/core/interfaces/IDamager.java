package team.zhkv.core.interfaces;

// import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Entity;

public interface IDamager {
    void giveDamage(Entity victim);

    // Coordinate getGoalCoordinate();
}
