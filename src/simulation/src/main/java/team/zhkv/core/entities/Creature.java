package team.zhkv.core.entities;

import team.zhkv.map.GameMap;
import team.zhkv.actions.move.Pathfinder;
import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IMoveble;

public abstract class Creature extends Entity implements IMoveble, IEater {
    private int hp;
    private Class<? extends Entity> food;
    private int speed;

    private Pathfinder pathfinder;

    protected Creature(int hp, Class<? extends Entity> food, int speed) {
        this.hp = hp;
        this.food = food;
        this.speed = speed;
        pathfinder = new Pathfinder();
    }

    @Override
    public void makeMove() {
        pathfinder.moveBySpeedOrTargetCell(speed);
    }

    public Class<? extends Entity> getFood() {
        return food;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp > 100 ? 100 : hp;
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinate getGoalCoordinate() {
        return pathfinder.getGoalCoordinate();
    }

    public void findPath(GameMap gm, Coordinate oldCoordinate) {
        pathfinder.build(this, gm, oldCoordinate);
    }
}
